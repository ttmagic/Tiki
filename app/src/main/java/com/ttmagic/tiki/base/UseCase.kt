package com.ttmagic.tiki.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 * P: Parameters.
 * R: Result.
 */
abstract class UseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param param the input parameters to run the use case with
     */
    @ExperimentalCoroutinesApi
    operator fun invoke(param: P): Flow<Result<R>> = execute(param)
        .onStart { emit(Result.Loading) }
        .catch { e -> emit(Result.Error(msg = e.message)) }
        .flowOn(dispatcher)

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract fun execute(param: P): Flow<Result<R>>
}
