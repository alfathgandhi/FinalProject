package com.d3if0093.dollareuroexcange.api

import com.d3if0093.dollareuroexcange.`object`.Kurs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET



private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.exchangerate-api.com/v6/")
        .build()

interface RequestApi {


        @GET("latest")
        fun getAll(): Call<Kurs>

}

object ListNegaraApi{
        val retrofitService = retrofit.create(
                RequestApi::class.java
        )
}