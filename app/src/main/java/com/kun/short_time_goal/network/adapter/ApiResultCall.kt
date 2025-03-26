package com.kun.short_time_goal.network.adapter

import com.kun.short_time_goal.network.model.ApiResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class ApiResultCall<R> constructor(
    private val delegate : Call<R>,
    private val successType : Type
) : Call<ApiResult<R>>{

    override fun enqueue(callback: Callback<ApiResult<R>>) = delegate.enqueue(object : Callback<R> {
        override fun onResponse(call: Call<R>, response: Response<R>) {
            val toResult = response.onApiResult()
            callback.onResponse(
                this@ApiResultCall,
                Response.success(toResult)
            )
        }

        override fun onFailure(call: Call<R>, t: Throwable) {
            callback.onResponse(this@ApiResultCall, Response.success(ApiResult.Fail.Exception(t as Exception)))
        }
    })

    override fun clone(): Call<ApiResult<R>> = ApiResultCall(delegate.clone(), successType)

    override fun execute(): Response<ApiResult<R>> = throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun Response<R>.onApiResult() : ApiResult<R> {
        val code = code()
        val body = body()
        val errorBody = errorBody()

        if(isSuccessful) {
            @Suppress("UNCHECKED_CAST")
            return ApiResult.Success(data = body as R)
        } else {
            return when (code) {
                in 500 .. 599 -> {
                    ApiResult.Fail.Error(
                        code = code,
                        message = "Server Internal Error ${errorBody?.string()}"
                    )
                }
                else -> {
                    val errorName = errorBody?.string() ?: "null error body"
                    ApiResult.Fail.Error(
                        code = code,
                        message = errorName
                    )
                }
            }
        }
    }
}
