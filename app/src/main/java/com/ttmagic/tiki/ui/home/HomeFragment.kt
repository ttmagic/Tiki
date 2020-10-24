package com.ttmagic.tiki.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ttmagic.tiki.R
import com.ttmagic.tiki.dpToPx
import com.ttmagic.tiki.setAddStatusBarPadding
import com.ttmagic.tiki.ui.home.epoxy.HomeController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_header.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val vm by viewModels<HomeVm>()

    @Inject
    lateinit var controller: HomeController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvLogo.setAddStatusBarPadding(true)
        swipeRefreshLayout.setDistanceToTriggerSync(300.dpToPx(requireContext()))
        recyclerView.adapter = controller.adapter

        vm.refreshHome()

        vm.homeViewState.observe(viewLifecycleOwner, Observer {
            controller.homeState = it
            swipeRefreshLayout.isRefreshing = false
        })

        swipeRefreshLayout.setOnRefreshListener {
            vm.refreshHome()
        }

        ivCart.setOnClickListener {
            navigate(url = "checkout/cart")
        }

        controller.onBannerClick = {
            navigate(withDomain = false, url = it.url)
        }
        controller.onQuickLinkClick = {
            navigate(withDomain = false,url = it.url)
        }
        controller.onFlashDealClick = {
            navigate(url = it.product.url_path)
        }
        controller.onCategoryClick = {
            navigate(url = it.url_key)
        }
        controller.onProductClick = {
            navigate(url = "${it.url_key}.html")
        }
    }

    /**
     * Use intent for view url
     * @param withDomain include domain or not.
     */
    private fun navigate(withDomain: Boolean = true, url: String) {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        if (withDomain) {
                            "https://tiki.vn/"
                        } else {
                            ""
                        } + url
                    )
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}