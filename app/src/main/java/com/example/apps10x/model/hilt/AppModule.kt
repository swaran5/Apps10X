package com.example.apps10x.model.hilt

import com.example.apps10x.model.network.RetroServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val baseURL = "https://api.openweathermap.org"

    @Singleton
    @Provides
    fun getRetroServiceInstance(retrofit: Retrofit) : RetroServiceInstance {
        return retrofit.create(RetroServiceInstance::class.java)
    }

    @Singleton
    @Provides
    fun getRetroInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}