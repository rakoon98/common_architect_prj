package com.kun.short_time_goal.network.adapter

import com.kun.short_time_goal.network.model.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Retrofit으로 생성할 Service Interface의 결과를 바꾸는 것은
 * CallAdapter.Factory에 커스텀을 주입하여 해결할 수 있습니다.
 */
class NetworkCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if(getRawType(returnType) != Call::class.java)
            return null

        check(returnType is ParameterizedType) {
            "Return Type Must be Defined ApiResult<Foo> or ApiResult<out Foo>"
        }

        val wrapperType = getParameterUpperBound(0, returnType)
        if (getRawType(wrapperType) != ApiResult::class.java)
            return null

        check(wrapperType is ParameterizedType) {
            "Return Type Must be Defined ApiResult<ResponseBody>"
        }

        val bodyType = getParameterUpperBound(0, wrapperType)
        return ApiResultCallAdapter<Any>(bodyType)
    }
}