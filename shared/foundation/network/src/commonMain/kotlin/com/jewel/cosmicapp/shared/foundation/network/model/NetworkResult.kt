package com.jewel.cosmicapp.shared.foundation.network.model

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val code: Int? = null, val message: String? = null, val throwable: Throwable? = null) : NetworkResult<Nothing>
    data object Loading : NetworkResult<Nothing>
}
