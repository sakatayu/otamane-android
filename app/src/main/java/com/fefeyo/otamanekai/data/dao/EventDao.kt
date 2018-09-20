package com.fefeyo.otamanekai.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.fefeyo.otamanekai.data.model.Event

@Dao
interface EventDao {
    @Insert
    fun insertEvent(vararg events: Event)
    @Delete
    fun deleteEvent(vararg events: Event)
}
