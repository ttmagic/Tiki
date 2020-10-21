package com.ttmagic.tiki.domain.home

import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.base.UseCase
import com.ttmagic.tiki.base.data
import com.ttmagic.tiki.data.TikiService
import com.ttmagic.tiki.di.IoDispatcher
import com.ttmagic.tiki.model.FlashDeal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFlashDealUseCase @Inject constructor(
    private val tikiService: TikiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Unit, List<FlashDeal>>(dispatcher) {

    override fun execute(param: Unit) = flow {
        val res = tikiService.getFlashDeal()
        if (res.data?.data.isNullOrEmpty()) {
            emit(Result.Error(204))
        } else {
            emit(Result.Success(res.data!!.data))
        }
    }

}
