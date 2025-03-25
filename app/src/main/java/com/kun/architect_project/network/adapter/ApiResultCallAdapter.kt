package com.kun.architect_project.network.adapter

import com.kun.architect_project.network.model.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ApiResultCallAdapter<R>(
    private val successType : Type
) : CallAdapter<R, Call<ApiResult<R>>>{
    override fun responseType(): Type = successType
    override fun adapt(call: Call<R>): Call<ApiResult<R>> = ApiResultCall(call, successType)
}