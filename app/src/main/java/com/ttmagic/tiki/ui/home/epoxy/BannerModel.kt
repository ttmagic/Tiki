package com.ttmagic.tiki.ui.home.epoxy

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.ttmagic.tiki.R
import com.ttmagic.tiki.ui.common.BaseEpoxyModel
import com.ttmagic.tiki.ui.common.EpoxyBaseViewHolder
import com.ttmagic.tiki.model.Banner
import kotlinx.android.synthetic.main.item_banner.view.*

@EpoxyModelClass(layout = R.layout.item_banner)
abstract class BannerModel : BaseEpoxyModel() {

    @EpoxyAttribute
    lateinit var banner: Banner

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onItemClick: (Banner) -> Unit

    override fun bind(holder: EpoxyBaseViewHolder) {
        holder.itemView.apply {
            setOnClickListener { onItemClick(banner) }
            Glide.with(context)
                .load(banner.image_url)
                .into(iv)
        }
    }
}