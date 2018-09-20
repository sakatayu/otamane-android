package com.fefeyo.otamanekai.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_work")
data class ProductWork(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 1,
        val productName: String,
        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
        val image: ByteArray? = null
)
