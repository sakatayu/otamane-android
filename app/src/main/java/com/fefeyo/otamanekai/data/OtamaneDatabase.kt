package com.fefeyo.otamanekai.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fefeyo.otamanekai.data.dao.EventDao
import com.fefeyo.otamanekai.data.dao.ProductWorkDao
import com.fefeyo.otamanekai.data.model.Event
import com.fefeyo.otamanekai.data.model.ProductWork
import com.fefeyo.otamanekai.data.model.converter.EventConverter

@Database(entities = [ProductWork::class, Event::class], version = 1, exportSchema = false)
@TypeConverters(EventConverter::class)
abstract class OtamaneDatabase : RoomDatabase() {

    abstract fun productWorkDao(): ProductWorkDao
    abstract fun eventDao(): EventDao

    companion object {
        private var instance: OtamaneDatabase? = null
        private const val DATABASE_NAME = "otamane.db"

        fun getInstance(context: Context): OtamaneDatabase =
                instance ?: synchronized(this) {
                    Room.databaseBuilder(context, OtamaneDatabase::class.java, DATABASE_NAME).build()
                }
    }

}
