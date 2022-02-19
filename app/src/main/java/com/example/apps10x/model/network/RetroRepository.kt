package com.example.apps10x.model.network

import androidx.lifecycle.MutableLiveData
import com.example.apps10x.model.WeatherResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroInstance: RetroServiceInstance) {

    fun<T> makeApiCall(endPoint: String, query: String, key: String, listener: ResponseListener<T>){

        var call: Call<T> = retroInstance.getWeather(query, key) as Call<T>

        when(endPoint){
            "weather" -> call = retroInstance.getWeather(query, key) as Call<T>
            "forecast" -> call = retroInstance.getForeCast(query, key) as Call<T>
        }

        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.isSuccessful){
                    listener.onSuccess(response.body() as T)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onError(t.message.toString())
            }

        })
    }
}
