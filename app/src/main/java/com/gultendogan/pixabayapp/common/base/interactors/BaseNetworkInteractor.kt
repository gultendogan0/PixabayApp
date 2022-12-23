package com.gultendogan.pixabayapp.common.base.interactors

import com.gultendogan.pixabayapp.R
import com.gultendogan.pixabayapp.common.base.wrappers.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

open class BaseNetworkInteractor {

    fun <T> safeApiCall(request: (suspend () -> Response<T>)) = flow {
        //show loading state
        emit(ResponseWrapper.Loading)

        kotlin.runCatching {
            request.invoke()
        }.onSuccess {
            println("response = $it")
            if (it.isSuccessful) {
                emit(ResponseWrapper.Success(it.body()!!))
            } else {
                emit(ResponseWrapper.Failure(it.message()))
            }
        }.onFailure {
            val errorMsg = catchException(it)
            emit(ResponseWrapper.LocalFailure(errorMsg))
        }
    }.flowOn(Dispatchers.IO)

    private fun catchException(throwable: Throwable) = when (throwable) {
        is HttpException -> R.string.error_internet_failure
        else -> R.string.error_service_failure
    }
}