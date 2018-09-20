package com.fefeyo.otamanekai.data.api

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class PagingResource<T>(
        val networkStatus: LiveData<NetworkStatus>,
        val data: LiveData<PagedList<T>>,
        val error: LiveData<Throwable>
)
