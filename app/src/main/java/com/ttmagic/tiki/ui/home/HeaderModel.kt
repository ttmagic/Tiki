package com.ttmagic.tiki.ui.home

import com.airbnb.epoxy.EpoxyModelClass
import com.ttmagic.tiki.R
import com.ttmagic.tiki.base.epoxy.BaseEpoxyModel
import com.ttmagic.tiki.base.epoxy.EpoxyBaseViewHolder

@EpoxyModelClass(layout = R.layout.item_header)
abstract class HeaderModel : BaseEpoxyModel() {

    override fun bind(holder: EpoxyBaseViewHolder) {
        super.bind(holder)
    }
}