package com.example.apps10x.model.network

import com.example.apps10x.model.WeatherResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInstance {


    @GET("/data/2.5/weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("APPID") key: String
    ): Call<WeatherResults>

}