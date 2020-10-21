package com.ttmagic.tiki.base

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultResponseAdapter<S : Any>(private val successType: Type) :
    CallAdapter<S, Call<Result<S>>> {
    override fun adapt(call: Call<S>): Call<Result<S>> {
        return ResultResponseCall(call)
    }

    override fun responseType(): Type = successType
}