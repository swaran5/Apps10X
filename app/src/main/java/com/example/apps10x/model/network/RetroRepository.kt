package com.example.apps10x.model.network

import androidx.lifecycle.MutableLiveData
import com.example.apps10x.model.WeatherResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroInstance: RetroServiceInstance) {

    fun<T> makeApiCall(query: String, key: String, listener: ResponseListener<T>){

        val call = retroInstance.getWeather(query, key)
        call.enqueue(object : Callback<WeatherResults>{
            override fun onResponse(
                call: Call<WeatherResults>,
                response: Response<WeatherResults>
            ) {
                if(response.isSuccessful){
                    listener.onSuccess(response.body() as T)
                }
            }

            override fun onFailure(call: Call<WeatherResults>, t: Throwable) {
                listener.onError(t.message.toString())
            }

        })
    }
}
