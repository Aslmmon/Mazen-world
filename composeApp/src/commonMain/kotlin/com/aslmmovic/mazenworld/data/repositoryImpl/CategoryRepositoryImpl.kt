package com.aslmmovic.mazenworld.data.repositoryImplimport

import androidx.compose.ui.tooling.data.position
import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.CategoryItem
import mazenworld.composeapp.generated.resources.category_alfabet
import mazenworld.composeapp.generated.resources.category_animals
import mazenworld.composeapp.generated.resources.category_shape
import mazenworld.composeapp.generated.resources.category_vehicles

import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mazenworld.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource

class CategoryRepositoryImpl : CategoryRepository {

    // Inject the Firestore instance. Koin will provide this.
    private val db: FirebaseFirestore = Firebase.firestore
    private val categoriesCollection = db.collection("categories")
    override fun getCategories(): Flow<List<CategoryItem>> {

        // Use snapshotFlow to listen for real-time updates from Firestore
        return categoriesCollection.snapshots().map { querySnapshot ->
            querySnapshot.documents.map { documentSnapshot ->
                // Automatically deserialize the document into our DTO
                val dto = documentSnapshot.data<CategoryDto>()
                // Map the DTO to the domain model (CategoryItem)
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

    /**
     * Maps the data layer object (CategoryDto) to the domain layer object (CategoryItem).
     * This is a crucial best practice to keep your domain pure.
     */
    private fun CategoryDto.toDomainModel(): CategoryItem {
        return CategoryItem(
            id = this.id,
            title = this.title,
            iconResource = getDrawableResourceFromString(this.iconResource), // Convert string to resource
            starCost = this.starCost,
            isLocked = this.isLocked,
            isPremiumContent = this.isPremiumContent,
        )
    }


    /**
     * A new mapper to convert a domain object back to a DTO for Firestore.
     */
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

    /**
     * A helper function to dynamically convert a string name from Firestore
     * into a Compose DrawableResource.
     *
     * You must add a case for each icon you have.
     */
    private fun getDrawableResourceFromString(iconName: String): DrawableResource {
        return when (iconName) {
            "ic_animals.xml" -> Res.drawable.category_animals
            "ic_vehicles.xml" -> Res.drawable.category_vehicles
            "ic_shapes.xml" -> Res.drawable.category_shape
            // Add other cases for all your category icons
            else -> Res.drawable.category_alfabet // A default fallback icon
        }
    }

    /**
     * A new helper to convert a DrawableResource back to its string name for Firestore.
     */
    private fun getStringFromDrawableResource(resource: DrawableResource): String {
        return when (resource) {
            Res.drawable.category_animals -> "ic_animals.xml"
            Res.drawable.category_vehicles -> "ic_vehicles.xml"
            Res.drawable.category_shape -> "ic_shapes.xml"
            Res.drawable.category_alfabet -> "ic_letters.xml"
            else -> "unknown_icon.xml" // Fallback for safety
        }
    }
}
