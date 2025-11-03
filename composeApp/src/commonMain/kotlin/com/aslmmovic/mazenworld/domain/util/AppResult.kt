package com.aslmmovic.mazenworld.domain.util


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 * @param <E>
 */
sealed class AppResult<out T, out E> {
    data class Success<out T>(val data: T) : AppResult<T, Nothing>()
    data class Failure<out E>(val error: E) : AppResult<Nothing, E>()
}


fun AppError.toUserFriendlyMessage(): String {
    return when (this) {
        is AppError.NetworkError -> "Please check your internet connection."
        is AppError.ServerError -> "Sorry, a server error occurred on our end."
        is AppError.Unknown -> this.message ?: "An unknown error occurred. Please try again."
    }
}