package com.ttmagic.tiki.ui.home.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.ttmagic.tiki.R
import com.ttmagic.tiki.ui.common.BaseEpoxyModel
import com.ttmagic.tiki.ui.common.EpoxyBaseViewHolder
import com.ttmagic.tiki.model.QuickLink
import kotlinx.android.synthetic.main.item_quick_link.view.*

@EpoxyModelClass(layout = R.layout.item_quick_link)
abstract class QuickLinkModel : BaseEpoxyModel() {

    @EpoxyAttribute
    lateinit var quickLink: QuickLink

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onItemClick: (QuickLink) -> Unit

    override fun bind(holder: EpoxyBaseViewHolder) {
        holder.itemView.apply {
            setOnClickListener { onItemClick(quickLink) }

            tvName.text = quickLink.title
            Glide.with(context)
                .load(quickLink.image_url)
                .into(ivIcon)
        }
    }
}