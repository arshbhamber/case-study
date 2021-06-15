package com.target.targetcasestudy.data.network

data class Resource<T>(val status: Status, val data: T? = null, val message: String? = null) {

    companion object{

        fun<T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun<T> error(message: String): Resource<T> = Resource(status = Status.ERROR, message = message)

        fun<T> loading(message: String? = null): Resource<T> = Resource(status = Status.LOADING, message = message)

    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

}