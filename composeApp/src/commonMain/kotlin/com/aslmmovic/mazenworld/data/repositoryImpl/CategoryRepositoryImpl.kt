package com.aslmmovic.mazenworld.data.repositoryImpl

import com.aslmmovic.mazenworld.data.model.CategoryDto
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import com.google.firebase.FirebaseException
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.net.SocketTimeoutException

class CategoryRepositoryImpl : CategoryRepository {

    private val db: FirebaseFirestore by lazy { Firebase.firestore }
    private val categoriesCollection by lazy { db.collection("categories") }

//    override fun getCategories(): Flow<List<CategoryDto>> {
//
//        // Use snapshotFlow to listen for real-time updates from Firestore
//        return categoriesCollection.snapshots().map { querySnapshot ->
//            querySnapshot.documents.map { documentSnapshot ->
//                documentSnapshot.data<CategoryDto>()
//
//            }
//        }
//    }

    // In com/aslmmovic/mazenworld/data/repositoryImpl/CategoryRepositoryImpl.kt

    override fun getCategories(): Flow<AppResult<List<CategoryDto>, AppError>> {

        return categoriesCollection.snapshots()
            .map { querySnapshot ->
                val categories = querySnapshot.documents.map { documentSnapshot ->
                    // 2. For each document, deserialize it to your DTO
                    documentSnapshot.data<CategoryDto>()
                }
                // The compiler can now infer this type, but being explicit is also fine
                AppResult.Success(categories) as AppResult<List<CategoryDto>, AppError>
            }
            .catch { e ->
                // 3. Simplify the catch block for KMP compatibility
                val error = when (e) {
                    is FirebaseException -> AppError.ServerError
                    else -> AppError.Unknown(e.message) // Catch other exceptions
                }
                emit(AppResult.Failure(error))
            }
    }


    override suspend fun publishCategories(categories: List<CategoryDto>): AppResult<Unit, AppError> {
        return try {
            val batch = db.batch()
            categories.forEach { categoryItem ->
                val documentRef = categoriesCollection.document(categoryItem.id)
                batch.set(documentRef, categoryItem)
            }
            batch.commit()
            AppResult.Success(Unit)
        } catch (e: FirebaseException) {
            AppResult.Failure(AppError.ServerError)
        } catch (e: Exception) {
            AppResult.Failure(AppError.Unknown(e.message))
        }
    }
//    override suspend fun publishCategories(categories: List<CategoryDto>) {
//        val batch = db.batch()
//        categories.forEach { categoryItem ->
//            val documentRef = categoriesCollection.document(categoryItem.id)
//            batch.set(documentRef, categoryItem)
//        }
//        batch.commit()
//    }


}
