package com.fefeyo.otamanekai.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product_work")
data class ProductWork(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 1,
        val name: String,
        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
        val image: ByteArray? = null,
        val favorite: Boolean = false
) : Parcelable
