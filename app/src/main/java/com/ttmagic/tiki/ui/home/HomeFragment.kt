package com.ttmagic.tiki.ui.home

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
            controller.setData(vm.listBanners.value, vm.listQuickLinks.value, vm.listFlashDeal.value)
            swipeRefreshLayout.isRefreshing = false
        })

        swipeRefreshLayout.setOnRefreshListener {
            vm.refreshHome()
        }
    }
}