package com.d3if0093.dollareuroexcange.adapter

import android.view.View
import com.d3if0093.dollareuroexcange.database.ListNegara

interface RecycleListClickListener {
    fun onRecyclerViewItemClicked(view: View, listNegara: ListNegara)
}