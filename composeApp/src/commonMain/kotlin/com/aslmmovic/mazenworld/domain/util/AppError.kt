package com.aslmmovic.mazenworld.domain.util


/**
 * A sealed class representing all possible errors in the application that the UI can understand.
 */
sealed class AppError {
    data object NetworkError : AppError()
    data object ServerError : AppError()
    data class Unknown(val message: String?) : AppError()
}
