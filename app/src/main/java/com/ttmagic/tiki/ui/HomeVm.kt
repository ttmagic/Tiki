package com.ttmagic.tiki.ui

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.domain.home.GetBannerUseCase
import com.ttmagic.tiki.domain.home.GetFlashDealUseCase
import com.ttmagic.tiki.domain.home.GetQuickLinkUseCase
import com.ttmagic.tiki.model.Banner
import com.ttmagic.tiki.model.FlashDeal
import com.ttmagic.tiki.model.QuickLink
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeVm
@ViewModelInject constructor(
    app: Application,
    private val getBannerUseCase: GetBannerUseCase,
    private val getQuickLinkUseCase: GetQuickLinkUseCase,
    private val getFlashDealUseCase: GetFlashDealUseCase
) : AndroidViewModel(app) {
    val listBanners = MutableLiveData<Result<List<Banner>>>()
    val listQuickLinks = MutableLiveData<Result<List<QuickLink>>>()
    val listFlashDeal = MutableLiveData<Result<List<FlashDeal>>>()

    val onHomeScreenUpdate = MediatorLiveData<String>().apply {
        addSource(listBanners) { value = "listBanners: $it" }
        addSource(listQuickLinks) { value = "listQuickLinks: $it" }
        addSource(listFlashDeal) { value = "listFlashDeal: $it" }
    }

    fun refreshHome() = viewModelScope.launch {
        listBanners.value = null
        listQuickLinks.value = null
        listFlashDeal.value = null
        Log.d("TTmagic", "refreshHome")

        async {
            async { getBanner() }
            async { getQuickLink() }
        }.invokeOnCompletion {
            getFlashDeal()
        }
    }

    private suspend fun getBanner(){
        Log.d("TTmagic", "getBanner")
        getBannerUseCase(Unit).collect { listBanners.postValue(it) }
    }

    private suspend fun getQuickLink() {
        Log.d("TTmagic", "getQuickLink")
        getQuickLinkUseCase(Unit).collect { listQuickLinks.postValue(it) }
    }

    private fun getFlashDeal() = viewModelScope.launch {
        Log.d("TTmagic", "getFlashDeal")
        getFlashDealUseCase(Unit).collect { listFlashDeal.postValue(it) }
    }

}

/**
 * Post Value into a liveData when collect.
 */
suspend fun <T> Flow<T>.onCollectPostValue(liveData: MutableLiveData<T>) {
    this.collect { liveData.postValue(it) }
}

