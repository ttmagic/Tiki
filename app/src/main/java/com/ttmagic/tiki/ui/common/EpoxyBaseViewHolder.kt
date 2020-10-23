package com.ttmagic.tiki.ui.common

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder

class EpoxyBaseViewHolder : EpoxyHolder() {
    lateinit var itemView: View
    override fun bindView(itemView: View) {
        this.itemView = itemView
    }
}

typealias BaseEpoxyModel = EpoxyModelWithHolder<EpoxyBaseViewHolder>