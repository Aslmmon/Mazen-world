package com.aslmmovic.mazenworld.data.repositoryImpl

import com.aslmmovic.mazenworld.data.model.GameQuestionDto
import com.aslmmovic.mazenworld.domain.respository.GamePlayRepository
import com.aslmmovic.mazenworld.domain.util.AppError
import com.aslmmovic.mazenworld.domain.util.AppResult
import com.google.firebase.FirebaseException
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GamePlayRepositoryImpl : GamePlayRepository {

    private val db: FirebaseFirestore by lazy { Firebase.firestore }

    private fun questionsCollection(categoryId: String) =
        db.collection(categoriesCollectionName).document(categoryId).collection("questions")

    override fun getQuestionsForCategory(categoryId: String): Flow<AppResult<List<GameQuestionDto>, AppError>> {
        return questionsCollection(categoryId).snapshots()
            .map { querySnapshot ->
                val questions = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.data<GameQuestionDto>()
                }
                AppResult.Success(questions) as AppResult<List<GameQuestionDto>, AppError>
            }
            .catch { e ->
                val error = when (e) {
                    is FirebaseException -> AppError.ServerError
                    else -> AppError.Unknown(e.message)
                }
                emit(AppResult.Failure(error))
            }
    }

    override suspend fun publishQuestions(
        categoryId: String,
        questions: List<GameQuestionDto>
    ): AppResult<Unit, AppError> {
        return try {
            val batch = db.batch()
            val collection = questionsCollection(categoryId)
            questions.forEach { question ->
                val documentRef = collection.document // Auto-generate ID
                batch.set(documentRef, question)
            }
            batch.commit()
            AppResult.Success(Unit)
        } catch (e: FirebaseException) {
            AppResult.Failure(AppError.ServerError)
        } catch (e: Exception) {
            AppResult.Failure(AppError.Unknown(e.message))
        }
    }
}