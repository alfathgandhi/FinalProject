package com.d3if0093.dollareuroexcange.database

import androidx.lifecycle.LiveData
import androidx.room.Query

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ListNegaraDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listNegara:ListNegara)

    @Query("SELECT * FROM ListNegara")
    fun getData():LiveData<List<ListNegara>>



    @Query("DELETE FROM ListNegara")
    fun clear()




}