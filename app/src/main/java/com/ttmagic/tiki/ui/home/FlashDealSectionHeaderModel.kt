package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.epoxy.BaseEpoxyModel
import com.ttmagic.tiki.base.epoxy.EpoxyBaseViewHolder
import kotlinx.android.synthetic.main.item_section_hot_deal.view.*

@EpoxyModelClass(layout = R.layout.item_section_hot_deal)
abstract class FlashDealSectionHeaderModel : BaseEpoxyModel() {

    override fun bind(holder: EpoxyBaseViewHolder) {
        holder.itemView.apply {
            Glide.with(context)
                .load(R.drawable.flash)
                .into(ivFlash)
        }
    }
}