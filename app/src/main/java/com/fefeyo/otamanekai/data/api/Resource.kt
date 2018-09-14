package com.fefeyo.otamanekai.data.api

data class Resource<out T>(val networkStatus: NetworkStatus, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(NetworkStatus.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable?): Resource<T> {
            return Resource(NetworkStatus.ERROR, null, error)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(NetworkStatus.LOADING, data, null)
        }
    }
}
