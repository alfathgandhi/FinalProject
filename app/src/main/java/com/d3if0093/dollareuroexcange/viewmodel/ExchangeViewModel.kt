package com.d3if0093.dollareuroexcange.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.d3if0093.dollareuroexcange.`object`.Kurs
import com.d3if0093.dollareuroexcange.api.ListNegaraApi
import com.d3if0093.dollareuroexcange.database.ListNegara
import com.d3if0093.dollareuroexcange.database.ListNegaraDAO
import com.d3if0093.dollareuroexcange.database.ListNegaraDatabase.Companion.getInstance
import com.d3if0093.dollareuroexcange.repository.DataRepository
import kotlinx.coroutines.*
import retrofit2.*

class ExchangeViewModel(application: Application):AndroidViewModel(application) {






    private var viewModelJob= SupervisorJob()
    private var viewModelScope= CoroutineScope(Dispatchers.Main+ this.viewModelJob)

    private val databse= getInstance(application)
    private val dataRepository= DataRepository(databse)




    private val _gagal=MutableLiveData<Boolean>()

            val gagal:LiveData<Boolean>
            get() = _gagal





    init {
        _gagal.value=false



    viewModelScope.launch {
        dataRepository.refreshData()


    }



}
    val dataNya=dataRepository.listNegara

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }






}




