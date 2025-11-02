package com.aslmmovic.mazenworld.data.repositoryImpl

import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl : CategoryRepository {

    private val db: FirebaseFirestore by lazy { Firebase.firestore }
    private val categoriesCollection by lazy { db.collection("categories") }

    override fun getCategories(): Flow<List<CategoryDto>> {

        // Use snapshotFlow to listen for real-time updates from Firestore
        return categoriesCollection.snapshots().map { querySnapshot ->
            querySnapshot.documents.map { documentSnapshot ->
                documentSnapshot.data<CategoryDto>()

            }
        }
    }

    override suspend fun publishCategories(categories: List<CategoryDto>) {
        val batch = db.batch()
        categories.forEach { categoryItem ->
            val documentRef = categoriesCollection.document(categoryItem.id)
            batch.set(documentRef, categoryItem)
        }
        batch.commit()
    }


}
