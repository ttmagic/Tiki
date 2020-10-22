package com.ttmagic.tiki.ui.home

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.epoxy.BaseEpoxyModel
import com.ttmagic.tiki.base.epoxy.EpoxyBaseViewHolder
import kotlinx.android.synthetic.main.item_section_header.view.*

@EpoxyModelClass(layout = R.layout.item_section_header)
abstract class SectionHeaderModel : BaseEpoxyModel() {

    @EpoxyAttribute
    var title: String? = null

    override fun bind(holder: EpoxyBaseViewHolder) {
        holder.itemView.apply {
            Glide.with(context)
                .load(R.drawable.flash)
                .into(ivFlash)
            if (!title.isNullOrBlank()) {
                customTitle.text = title
                customTitle.visibility = View.VISIBLE
            }
        }
    }
}