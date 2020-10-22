package com.ttmagic.tiki.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ttmagic.tiki.R
import com.ttmagic.tiki.setAddStatusBarPadding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    val vm by viewModels<HomeVm>()

    @Inject
    lateinit var controller: HomeController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setAddStatusBarPadding(true)
        recyclerView.adapter = controller.adapter

        vm.refreshHome()

        vm.onHomeScreenUpdate.observe(viewLifecycleOwner, Observer {
            controller.setData(
                vm.listBanners.value,
                vm.listQuickLinks.value,
                vm.listFlashDeal.value
            )
            swipeRefreshLayout.isRefreshing = false
        })

        swipeRefreshLayout.setOnRefreshListener {
            vm.refreshHome()
        }

        controller.onBannerClick = {
            navigate(it.url)
        }
        controller.onQuickLinkClick = {
            navigate(it.url)
        }
        controller.onFlashDealClick = {
            navigate("https://tiki.vn/${it.product.url_path}")
        }
    }

    private fun navigate(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}