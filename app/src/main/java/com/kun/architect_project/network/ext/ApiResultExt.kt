package com.kun.architect_project.network.ext

import com.kun.architect_project.network.model.ApiResult

inline fun <reified T : Any> ApiResult<T>.onSuccess(action: (data: T) -> Unit) {
    if (this is ApiResult.Success) action(data)
}

inline fun <reified T : Any> ApiResult<T>.onError(action: (code: Int, message: String?) -> Unit) {
    if (this is ApiResult.Fail.Error) action(code, message)
}

inline fun <reified T : Any> ApiResult<T>.onException(action: (e: Throwable) -> Unit) {
    if (this is ApiResult.Fail.Exception) action(e)
}