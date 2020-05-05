package com.d3if0093.dollareuroexcange.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.d3if0093.dollareuroexcange.database.ListNegaraDatabase.Companion.getInstance
import com.d3if0093.dollareuroexcange.repository.DataRepository
import kotlinx.coroutines.*


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







}
    val dataNya=dataRepository.getListNegara()

    fun refresh(){
        viewModelScope.launch {
             dataRepository.refreshData()


        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }






}




