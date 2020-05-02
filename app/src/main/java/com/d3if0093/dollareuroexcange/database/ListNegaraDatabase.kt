package com.d3if0093.dollareuroexcange.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListNegara::class],version = 1, exportSchema = false)
abstract class ListNegaraDatabase:RoomDatabase() {

    abstract val ListNegaraDAO: ListNegaraDAO

    companion object {

        @Volatile
        private var INSTANCE: ListNegaraDatabase? = null

        fun getInstance(context: Context): ListNegaraDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ListNegaraDatabase::class.java,
                        "ListNegara_database"
                    )

                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}