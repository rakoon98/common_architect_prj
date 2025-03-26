package com.kun.short_time_goal.network.model

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()

    sealed class Fail : ApiResult<Nothing>() {
        data class Error(val code: Int, val message: String?) : Fail()
        data class Exception(val e:Throwable) : Fail()
    }
}