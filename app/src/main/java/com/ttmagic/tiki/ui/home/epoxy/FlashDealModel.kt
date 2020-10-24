package com.ttmagic.tiki.ui.home.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ttmagic.tiki.R
import com.ttmagic.tiki.ui.common.BaseEpoxyModel
import com.ttmagic.tiki.ui.common.EpoxyBaseViewHolder
import com.ttmagic.tiki.dpToPx
import com.ttmagic.tiki.formatDiscount
import com.ttmagic.tiki.model.FlashDeal
import kotlinx.android.synthetic.main.item_flash_deal.view.*

@EpoxyModelClass(layout = R.layout.item_flash_deal)
abstract class FlashDealModel : BaseEpoxyModel() {

    @EpoxyAttribute
    lateinit var flashDeal: FlashDeal

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onItemClick: (FlashDeal) -> Unit

    override fun bind(holder: EpoxyBaseViewHolder) {
        holder.itemView.apply {
            setOnClickListener { onItemClick.invoke(flashDeal) }

            tvDiscount.formatDiscount(flashDeal.discount_percent)
            tvProductPrice.text =
                String.format(resources.getString(R.string.format_price), flashDeal.special_price)
            tvProgressText.text = flashDeal.progress.progress_text
            progressBar.progress = 100 - flashDeal.progress.percent
            Glide.with(context)
                .load(flashDeal.product.thumbnail_url)
                .into(ivProduct)
        }
    }
}