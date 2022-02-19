package com.example.apps10x.model.network

interface ResponseListener<T> {

    fun onSuccess(value: T)

    fun onError(error: String)

}