package com.ttmagic.tiki.ui.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ttmagic.tiki.base.Result
import com.ttmagic.tiki.domain.home.*
import com.ttmagic.tiki.model.*
import com.ttmagic.tiki.onCollectPostValue
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeVm
@ViewModelInject constructor(
    app: Application,
    private val getBannerUseCase: GetBannerUseCase,
    private val getQuickLinkUseCase: GetQuickLinkUseCase,
    private val getFlashDealUseCase: GetFlashDealUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val getProductUseCase: GetProductUseCase
) : AndroidViewModel(app) {
    private val listBanners = MutableLiveData<Result<List<Banner>>>()
    private val listQuickLinks = MutableLiveData<Result<List<QuickLink>>>()
    private val listFlashDeal = MutableLiveData<Result<List<FlashDeal>>>()
    private val listCategories = MutableLiveData<Result<List<Category>>>()
    private val listProducts = MutableLiveData<Result<List<Product>>>()

    val homeViewState = MediatorLiveData<HomeViewState>().apply {
        value = HomeViewState()
        addSource(listBanners) { value = value?.copy(listBanners = listBanners.value) }
        addSource(listQuickLinks) { value = value?.copy(listQuickLinks = listQuickLinks.value) }
        addSource(listFlashDeal) { value = value?.copy(listFlashDeal = listFlashDeal.value) }
        addSource(listCategories) { value = value?.copy(listCategories = listCategories.value) }
        addSource(listProducts) { value = value?.copy(listProducts = listProducts.value) }
    }

    fun refreshHome() = viewModelScope.launch {
        listBanners.value = null
        listQuickLinks.value = null
        listFlashDeal.value = null
        listCategories.value = null
        listProducts.value = null

        async {
            async { getBanner() }
            async { getQuickLink() }
        }.invokeOnCompletion {
            getFlashDeal()
            getCategories()
            getProducts()
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

    private fun getProducts() = viewModelScope.launch {
        val param = ProductQuery(page = 0, limit = 20)
        getProductUseCase(param).onCollectPostValue(listProducts)
    }

}