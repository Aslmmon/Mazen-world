package com.aslmmovic.mazenworld.data.repositoryImpl

import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.CategoryItem
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import mazenworld.composeapp.generated.resources.Res
import mazenworld.composeapp.generated.resources.category_alfabet
import mazenworld.composeapp.generated.resources.category_animals
import mazenworld.composeapp.generated.resources.category_shape
import mazenworld.composeapp.generated.resources.category_vehicles
import org.jetbrains.compose.resources.DrawableResource

class CategoryRepositoryImpl : CategoryRepository {

    private val db: FirebaseFirestore by lazy { Firebase.firestore }
    private val categoriesCollection by lazy { db.collection("categories") }

    override fun getCategories(): Flow<List<CategoryItem>> {

        // Use snapshotFlow to listen for real-time updates from Firestore
        return categoriesCollection.snapshots().map { querySnapshot ->
            querySnapshot.documents.map { documentSnapshot ->
                val dto = documentSnapshot.data<CategoryDto>()
                dto.toDomainModel()
            }
        }
    }

    override suspend fun publishCategories(categories: List<CategoryItem>) {
        val batch = db.batch()
        categories.forEach { categoryItem ->
            val dto = categoryItem.toDto()
            val documentRef = categoriesCollection.document(dto.id)
            batch.set(documentRef, dto)
        }
        batch.commit()
    }

    private fun CategoryDto.toDomainModel(): CategoryItem {
        return CategoryItem(
            id = this.id,
            title = this.title,
            iconResource = getDrawableResourceFromString(this.iconResource),
            starCost = this.starCost,
            isLocked = this.isLocked,
            isPremiumContent = this.isPremiumContent,
        )
    }

    private fun CategoryItem.toDto(): CategoryDto {
        return CategoryDto(
            id = this.id,
            title = this.title,
            iconResource = getStringFromDrawableResource(this.iconResource),
            starCost = this.starCost,
            isLocked = this.isLocked,
            isPremiumContent = this.isPremiumContent,
        )
    }

    private fun getDrawableResourceFromString(iconName: String): DrawableResource {
        return when (iconName) {
            "ic_animals.xml" -> Res.drawable.category_animals
            "ic_vehicles.xml" -> Res.drawable.category_vehicles
            "ic_shapes.xml" -> Res.drawable.category_shape
            else -> Res.drawable.category_alfabet
        }
    }

    private fun getStringFromDrawableResource(resource: DrawableResource): String {
        return when (resource) {
            Res.drawable.category_animals -> "ic_animals.xml"
            Res.drawable.category_vehicles -> "ic_vehicles.xml"
            Res.drawable.category_shape -> "ic_shapes.xml"
            Res.drawable.category_alfabet -> "ic_letters.xml"
            else -> "unknown_icon.xml"
        }
    }
}
