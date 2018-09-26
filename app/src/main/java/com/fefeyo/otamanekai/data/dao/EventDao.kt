package com.fefeyo.otamanekai.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fefeyo.otamanekai.data.model.Event

@Dao
interface EventDao {
    @Query("select * from event")
    fun findAll(): LiveData<List<Event>>
    @Insert
    fun insertEvent(vararg events: Event)
    @Delete
    fun deleteEvent(vararg events: Event)
}
