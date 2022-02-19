package com.example.apps10x.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apps10x.model.EndPoints
import com.example.apps10x.model.ServiceBuilder
import com.example.apps10x.model.WeatherResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel : ViewModel() {

    private val request = ServiceBuilder.buildService(EndPoints::class.java)
    private val key = "9b8cb8c7f11c077f8c4e217974d9ee40"
    private var weather = MutableLiveData<WeatherResults>()

    fun getWeather(): MutableLiveData<WeatherResults> {
        val call = request.getWeather("Bengaluru", key)

        call.enqueue(object : Callback<WeatherResults> {
            override fun onResponse(
                call: Call<WeatherResults>,
                response: Response<WeatherResults>
            ) {
                if(response.isSuccessful){
                    weather.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<WeatherResults>, t: Throwable) {
                print(t.toString())
            }

        })
        return weather
    }


}