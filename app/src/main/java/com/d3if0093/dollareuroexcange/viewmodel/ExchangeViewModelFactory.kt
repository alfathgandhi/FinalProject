package com.d3if0093.dollareuroexcange.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0093.dollareuroexcange.database.ListNegaraDAO

class ExchangeViewModelFactory (
    private val dataSource: ListNegaraDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeViewModel::class.java)) {
            return ExchangeViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}