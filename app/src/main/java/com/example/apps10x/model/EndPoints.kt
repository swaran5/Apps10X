package com.example.apps10x.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoints {

    @GET("/data/2.5/weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("APPID") key: String
        ): Call<WeatherResults>

}
