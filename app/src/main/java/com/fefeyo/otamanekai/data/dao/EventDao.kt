package com.fefeyo.otamanekai.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fefeyo.otamanekai.data.model.Event
import org.threeten.bp.LocalDate

@Dao
interface EventDao {
    @Query("select * from event order by id desc")
    fun findAll(): LiveData<List<Event>>

    @Query("select * from event where productId = :productId order by id desc")
    fun findAll(productId: Long): LiveData<List<Event>>

    @Query("select * from event where date = :date")
    fun findAllfromDate(date: LocalDate): LiveData<List<Event>>

    @Insert
    fun insertEvent(vararg events: Event)

    @Delete
    fun deleteEvent(vararg events: Event)
}
