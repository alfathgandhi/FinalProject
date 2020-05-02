package com.d3if0093.dollareuroexcange.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.d3if0093.dollareuroexcange.R
import com.d3if0093.dollareuroexcange.`object`.Kurs
import com.d3if0093.dollareuroexcange.`object`.Rates
import com.d3if0093.dollareuroexcange.api.ListNegaraApi
import com.d3if0093.dollareuroexcange.api.RequestApi
import com.d3if0093.dollareuroexcange.database.ListNegara
import com.d3if0093.dollareuroexcange.database.ListNegaraDAO
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExchangeViewModel(val database: ListNegaraDAO, application: Application):AndroidViewModel(application) {






    private var viewModelJob= Job()
    private var uiScope= CoroutineScope(Dispatchers.Main+viewModelJob)
    val dataNya= database.getData()

    private val _data = MutableLiveData<Kurs>()

    val dataApi:LiveData<Kurs>
    get() = _data

    private val _gagal=MutableLiveData<Boolean>()

            val gagal:LiveData<Boolean>
            get() = _gagal





    init {
        _gagal.value=false

        dataApi()

    uiScope.launch {

            clearData()
            insertData(ListNegara(0, "United States America", "USD", R.drawable.amerika))
            insertData(ListNegara(0, "Great Britain", "GBP", R.drawable.uk))
            insertData(ListNegara(0, "Indonesia", "IDR", R.drawable.indo))
            insertData(ListNegara(0, "Singapore", "SGD", R.drawable.sg))
            insertData(ListNegara(0, "Australia", "AUD", R.drawable.australi))
            insertData(ListNegara(0, "China", "CNY", R.drawable.china))
            insertData(ListNegara(0, "Japan", "JPY", R.drawable.jpn))
            insertData(ListNegara(0, "European", "EUR", R.drawable.euro))

    }



}

    private fun dataApi(){


                val call = ListNegaraApi.retrofitService.getAll()
                    call.enqueue(object:Callback<Kurs>{
                        override fun onFailure(call: Call<Kurs>, t: Throwable) {

                            _gagal.value=true


                        }

                        override fun onResponse(call: Call<Kurs>, response: Response<Kurs>) {
                            _data.value=response.body()



                        }
                    })









    }












     private suspend fun insertData(listNegara: ListNegara){
        withContext(Dispatchers.IO){
            database.insert(listNegara)
        }


    }

    private suspend fun clearData(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }




}




