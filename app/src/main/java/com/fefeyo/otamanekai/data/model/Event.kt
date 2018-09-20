package com.fefeyo.otamanekai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "event")
data class Event(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 1,
        val eventName: String,
        val date: Date,
        val eventType: EventType,
        val productId: Long
)
