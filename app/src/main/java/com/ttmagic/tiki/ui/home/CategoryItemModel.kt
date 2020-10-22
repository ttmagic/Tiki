package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.epoxy.BaseEpoxyModel
import com.ttmagic.tiki.base.epoxy.EpoxyBaseViewHolder
import com.ttmagic.tiki.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

@EpoxyModelClass(layout = R.layout.item_category)
abstract class CategoryItemModel : BaseEpoxyModel() {

    @EpoxyAttribute
    lateinit var category: Category

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onItemClick: (Category) -> Unit

    override fun bind(holder: EpoxyBaseViewHolder) {
        holder.itemView.apply {
            setOnClickListener { onItemClick(category) }

            tvName.text = category.name
            Glide.with(context)
                .load(category.thumbnail_url)
                .into(ivIcon)
        }
    }
}