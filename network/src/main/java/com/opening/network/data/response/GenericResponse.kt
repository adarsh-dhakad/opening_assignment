package com.opening.network.data.response

sealed class GenericResponse<out T> {

    class Success<out T>(val data: T) : GenericResponse<T>()
    class Error(val message: String) : GenericResponse<Nothing>()
    object Loading : GenericResponse<Nothing>()
}
