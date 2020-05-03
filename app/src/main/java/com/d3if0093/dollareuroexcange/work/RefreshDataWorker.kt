package com.d3if0093.dollareuroexcange.work

import android.content.Context
import android.service.voice.AlwaysOnHotwordDetector

import  androidx.work.CoroutineWorker
import androidx.work.Data

import androidx.work.WorkerParameters

import com.d3if0093.dollareuroexcange.database.ListNegaraDatabase.Companion.getInstance
import com.d3if0093.dollareuroexcange.repository.DataRepository
import retrofit2.HttpException


class RefreshDataWorker (appContext: Context, params:WorkerParameters):
        CoroutineWorker(appContext, params){

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database= getInstance(applicationContext)
        val repository = DataRepository(database)
        return try{
            repository.refreshData()
            Result.success()
        }catch (e:HttpException){
            Result.retry()
        }
    }
}