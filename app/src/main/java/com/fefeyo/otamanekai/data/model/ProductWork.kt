package com.fefeyo.otamanekai.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * 作品情報
 * @param id プライマリーID
 * @param name 作品名
 * @param image サムネイル画像
 * @param favorite お気に入り登録してるか
 */
@Parcelize
@Entity(tableName = "product_work")
data class ProductWork(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val name: String,
        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
        val image: ByteArray? = null,
        val favorite: Boolean? = false
) : Parcelable {
    companion object {
        fun empty() = ProductWork(0, "")
    }
}
