package com.d3if0093.dollareuroexcange.repository

import androidx.lifecycle.LiveData

import com.d3if0093.dollareuroexcange.R
import com.d3if0093.dollareuroexcange.`object`.Rates
import com.d3if0093.dollareuroexcange.api.ListNegaraApi
import com.d3if0093.dollareuroexcange.database.ListNegara
import com.d3if0093.dollareuroexcange.database.ListNegaraDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class DataRepository(private val database:ListNegaraDatabase){
   fun getListNegara() :LiveData<List<ListNegara>>{

          return database.ListNegaraDAO.getData()
      }


    suspend fun refreshData(){
        withContext(Dispatchers.IO){
            val call = ListNegaraApi.retrofitService.getAll().await()






                    val rates: Rates? = call?.rates
                    database.ListNegaraDAO.insert(ListNegara(0, "United States America", "USD", R.drawable.amerika,rates?.USD.toString()))
                    database.ListNegaraDAO.insert(ListNegara(1, "Great Britain", "GBP", R.drawable.uk,rates?.GBP.toString()))
                    database.ListNegaraDAO.insert(ListNegara(2, "Indonesia", "IDR", R.drawable.indo,rates?.IDR.toString()))
                    database.ListNegaraDAO.insert(ListNegara(3, "Singapore", "SGD", R.drawable.sg,rates?.SGD.toString()))
                    database.ListNegaraDAO.insert(ListNegara(4, "Australia", "AUD", R.drawable.australi,rates?.AUD.toString()))
                    database.ListNegaraDAO.insert(ListNegara(5, "China", "CNY", R.drawable.china,rates?.CNY.toString()))
                    database.ListNegaraDAO.insert(ListNegara(6, "Japan", "JPY", R.drawable.jpn,rates?.JPY.toString()))
                    database.ListNegaraDAO.insert(ListNegara(7, "Europe", "EUR", R.drawable.euro,rates?.EUR.toString()))




        }
    }
}