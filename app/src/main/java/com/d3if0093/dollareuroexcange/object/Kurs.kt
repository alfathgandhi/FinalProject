package com.d3if0093.dollareuroexcange.`object`

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Kurs (
   var base_code:String,
   val rates: Rates? = null

    )
