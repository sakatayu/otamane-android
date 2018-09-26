package com.fefeyo.otamanekai.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fefeyo.otamanekai.data.dao.EventDao
import com.fefeyo.otamanekai.data.dao.ProductWorkDao
import com.fefeyo.otamanekai.data.model.Event
import com.fefeyo.otamanekai.data.model.ProductWork

@Database(entities = [ProductWork::class, Event::class], version = 1, exportSchema = false)
abstract class OtamaneDatabase : RoomDatabase() {

    abstract fun productWorkDao(): ProductWorkDao
    abstract fun eventDao(): EventDao

    companion object {
        private var instance: OtamaneDatabase? = null
        const val DATABASE_NAME = "otamane.db"

        fun getInstance(context: Context): OtamaneDatabase =
                instance ?: synchronized(this) {
                    Room.databaseBuilder(context, OtamaneDatabase::class.java, DATABASE_NAME).build()
                }
    }

}
