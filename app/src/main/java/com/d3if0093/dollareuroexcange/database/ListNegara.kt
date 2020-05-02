package com.d3if0093.dollareuroexcange.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "ListNegara")
data class ListNegara(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,
    @ColumnInfo(name = "Negara")
    var negara:String,
    @ColumnInfo(name="MataUang")
    var mataUang:String,
    @ColumnInfo(name="Pict")
    var pict:Int
)