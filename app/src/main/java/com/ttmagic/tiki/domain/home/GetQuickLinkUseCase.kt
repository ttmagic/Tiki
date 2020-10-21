package com.ttmagic.tiki.domain.home

import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.base.UseCase
import com.ttmagic.tiki.base.data
import com.ttmagic.tiki.data.TikiService
import com.ttmagic.tiki.di.IoDispatcher
import com.ttmagic.tiki.model.QuickLink
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuickLinkUseCase @Inject constructor(
    private val tikiService: TikiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : UseCase<Unit, List<QuickLink>>(dispatcher) {

    override fun execute(param: Unit) = flow {
        val res = tikiService.getQuickLink()
        if (res.data?.data.isNullOrEmpty()) {
            emit(Result.Error(204))
        } else {
            val allQuickLinks = arrayListOf<QuickLink>()
            res.data?.data?.forEach { allQuickLinks.addAll(it) }
            emit(Result.Success(allQuickLinks))
        }
    }

}
