package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.epoxy.BaseEpoxyModel
import com.ttmagic.tiki.base.epoxy.EpoxyBaseViewHolder
import com.ttmagic.tiki.model.QuickLink
import kotlinx.android.synthetic.main.item_quick_link.view.*

@EpoxyModelClass(layout = R.layout.item_quick_link)
abstract class QuickLinkItemModel : BaseEpoxyModel() {

    @EpoxyAttribute
    lateinit var quickLink: QuickLink

    override fun bind(holder: EpoxyBaseViewHolder) {
        holder.itemView.apply {
            tvName.text = quickLink.title
            Glide.with(context)
                .load(quickLink.image_url)
                .into(ivIcon)
        }
    }
}