package com.d3if0093.dollareuroexcange.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.android.synthetic.main.excange_list.view.*

@Entity(tableName = "ListNegara")
data class ListNegara(
    @PrimaryKey
    var id:Long,
    @ColumnInfo(name = "Negara")
    var negara:String,
    @ColumnInfo(name="MataUang")
    var mataUang:String,
    @ColumnInfo(name="Pict")
    var pict:Int,
    @ColumnInfo(name="Value")
    var value:String?
)

