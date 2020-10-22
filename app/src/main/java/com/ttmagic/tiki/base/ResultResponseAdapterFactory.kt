package com.ttmagic.tiki.base

import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Custom CallAdapter for parsing Result Response.
 * Usage: Retrofit.Builder().addCallAdapterFactory(ResultResponseAdapterFactory())
 */
class ResultResponseAdapterFactory :CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        // suspend functions wrap the response type in `Call`
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<Result<<Foo>> or Call<Result<out Foo>>"
        }

        // get the response type inside the `Call` type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not Result then we can't handle this type, so we return null
        if (getRawType(responseType) != Result::class.java) {
            return null
        }

        // the response type is ApiResponse and should be parameterized
        check(responseType is ParameterizedType) { "Response must be parameterized as Result<Foo> or Result<out Foo>" }

        val successBodyType = getParameterUpperBound(0, responseType)

        return ResultResponseAdapter<Any>(successBodyType)
    }
}


class ResultResponseCall<S>(private val delegate: Call<S>) : Call<Result<S>> {

    override fun enqueue(callback: Callback<Result<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@ResultResponseCall,
                            Response.success(Result.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@ResultResponseCall,
                            Response.success(
                                Result.Error(code, "Response is successful but the body is null")
                            )
                        )
                    }
                } else {
                    callback.onResponse(
                        this@ResultResponseCall,
                        Response.success(Result.Error(code, error.toString()))
                    )
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                callback.onFailure(this@ResultResponseCall, throwable)
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = ResultResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<Result<S>> {
        throw UnsupportedOperationException("ResultResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}

class ResultResponseAdapter<S : Any>(private val successType: Type) :
    CallAdapter<S, Call<Result<S>>> {
    override fun adapt(call: Call<S>): Call<Result<S>> {
        return ResultResponseCall(call)
    }

    override fun responseType(): Type = successType
}