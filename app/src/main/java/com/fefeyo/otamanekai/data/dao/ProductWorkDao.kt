package com.fefeyo.otamanekai.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.fefeyo.otamanekai.data.model.ProductWork

@Dao
interface ProductWorkDao {
    @Query("select * from product_work")
    fun findAll(): LiveData<List<ProductWork>>
    @Insert
    fun insert(productWork: ProductWork)
    @Update
    fun update(productWork: ProductWork)
    @Delete
    fun delete(productWork: ProductWork)
}
