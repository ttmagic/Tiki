package com.ttmagic.tiki.base

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
