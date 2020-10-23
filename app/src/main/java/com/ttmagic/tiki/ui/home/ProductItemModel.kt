package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.epoxy.BaseEpoxyModel
import com.ttmagic.tiki.base.epoxy.EpoxyBaseViewHolder
import com.ttmagic.tiki.dpToPx
import com.ttmagic.tiki.formatDiscount
import com.ttmagic.tiki.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

@EpoxyModelClass(layout = R.layout.item_product)
abstract class ProductItemModel : BaseEpoxyModel() {

    @EpoxyAttribute
    lateinit var product: Product

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onItemClick: (Product) -> Unit

    override fun bind(holder: EpoxyBaseViewHolder) {
        holder.itemView.apply {
            setOnClickListener { onItemClick.invoke(product) }
            tvProductName.text = product.name
            tvDiscount.formatDiscount(product.discount_rate)
            tvProductPrice.text =
                String.format(resources.getString(R.string.format_price), product.list_price)
            Glide.with(context)
                .load(product.thumbnail_url)
                .transform(RoundedCorners(10.dpToPx(context)))
                .into(ivProduct)
        }
    }
}