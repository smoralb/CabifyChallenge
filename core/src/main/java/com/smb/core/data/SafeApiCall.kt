package com.smb.core.data

import retrofit2.Response

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R
): Result<R> {
    val response: Response<T>
    return try {
        response = apiCall.invoke()
        Result.success(mapper(response.body()!!))
    } catch (exception: Throwable) {
        Result.failure(exception)
    }
}