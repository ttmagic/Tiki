package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.epoxy.BaseEpoxyModel
import com.ttmagic.tiki.base.epoxy.EpoxyBaseViewHolder
import com.ttmagic.tiki.dpToPx
import com.ttmagic.tiki.model.FlashDeal
import kotlinx.android.synthetic.main.item_flash_deal.view.*

@EpoxyModelClass(layout = R.layout.item_flash_deal)
abstract class FlashDealItemModel : BaseEpoxyModel() {

    @EpoxyAttribute
    lateinit var flashDeal: FlashDeal

    override fun bind(holder: EpoxyBaseViewHolder) {
        holder.itemView.apply {
            tvDiscount.text = "-${flashDeal.discount_percent}%"
            tvProductPrice.text =
                String.format(resources.getString(R.string.format_price), flashDeal.special_price)
            tvProgressText.text = flashDeal.progress.progress_text
            progressBar.progress = 100 - flashDeal.progress.percent
            Glide.with(context)
                .load(flashDeal.product.thumbnail_url)
                .transform(RoundedCorners(10.dpToPx(context)))
                .into(ivProduct)
        }
    }
}