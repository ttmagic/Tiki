package com.ttmagic.tiki.domain.home

import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.base.UseCase
import com.ttmagic.tiki.base.data
import com.ttmagic.tiki.data.TikiService
import com.ttmagic.tiki.di.IoDispatcher
import com.ttmagic.tiki.model.Product
import com.ttmagic.tiki.model.ProductQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetProductUseCase @Inject constructor(
    private val tikiService: TikiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<ProductQuery, List<Product>>(dispatcher) {

    override fun execute(param: ProductQuery) = flow {
        val res = tikiService.getProducts(param.toQueryMap())
        if (res.data?.data.isNullOrEmpty()) {
            if (res is Result.Error) emit(res as Result.Error) else emit(Result.Error(204))
        } else {
            emit(Result.Success(res.data!!.data))
        }
    }

}
