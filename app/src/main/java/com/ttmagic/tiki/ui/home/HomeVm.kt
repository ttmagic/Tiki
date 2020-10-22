package com.ttmagic.tiki.ui.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.domain.home.GetBannerUseCase
import com.ttmagic.tiki.domain.home.GetCategoryUseCase
import com.ttmagic.tiki.domain.home.GetFlashDealUseCase
import com.ttmagic.tiki.domain.home.GetQuickLinkUseCase
import com.ttmagic.tiki.model.Banner
import com.ttmagic.tiki.model.Category
import com.ttmagic.tiki.model.FlashDeal
import com.ttmagic.tiki.model.QuickLink
import com.ttmagic.tiki.onCollectPostValue
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeVm
@ViewModelInject constructor(
    app: Application,
    private val getBannerUseCase: GetBannerUseCase,
    private val getQuickLinkUseCase: GetQuickLinkUseCase,
    private val getFlashDealUseCase: GetFlashDealUseCase,
    private val getCategoryUseCase: GetCategoryUseCase
) : AndroidViewModel(app) {
    val listBanners = MutableLiveData<Result<List<Banner>>>()
    val listQuickLinks = MutableLiveData<Result<List<QuickLink>>>()
    val listFlashDeal = MutableLiveData<Result<List<FlashDeal>>>()
    val listCategories = MutableLiveData<Result<List<Category>>>()

    val onHomeScreenUpdate = MediatorLiveData<String>().apply {
        addSource(listBanners) { value = "listBanners: $it" }
        addSource(listQuickLinks) { value = "listQuickLinks: $it" }
        addSource(listFlashDeal) { value = "listFlashDeal: $it" }
        addSource(listCategories) { value = "listCategories: $it" }
    }

    fun refreshHome() = viewModelScope.launch {
        listBanners.value = null
        listQuickLinks.value = null
        listFlashDeal.value = null
        listCategories.value = null

        async {
            async { getBanner() }
            async { getQuickLink() }
        }.invokeOnCompletion {
            getFlashDeal()
            getCategories()
        }
    }

    private suspend fun getBanner() {
        getBannerUseCase(Unit).onCollectPostValue(listBanners)
    }

    private suspend fun getQuickLink() {
        getQuickLinkUseCase(Unit).onCollectPostValue(listQuickLinks)
    }

    private fun getFlashDeal() = viewModelScope.launch {
        getFlashDealUseCase(Unit).onCollectPostValue(listFlashDeal)
    }

    private fun getCategories() = viewModelScope.launch {
        getCategoryUseCase(Unit).onCollectPostValue(listCategories)
    }

}