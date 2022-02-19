package com.example.apps10x.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apps10x.model.ForeCast
import com.example.apps10x.model.WeatherResults
import com.example.apps10x.model.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.apps10x.model.network.ResponseListener

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository : RetroRepository) : ViewModel() {

    private val key = "9b8cb8c7f11c077f8c4e217974d9ee40"
    private var weather = MutableLiveData<WeatherResults>()
    private var foreCast = MutableLiveData<ForeCast>()


    fun getWeather() : MutableLiveData<WeatherResults>{
        repository.makeApiCall("weather","Bengaluru", key, object : ResponseListener<WeatherResults>{
            override fun onSuccess(value: WeatherResults) {
                weather.postValue(value)
            }

            override fun onError(error: String) {
                weather.postValue(null)
            }

        })

        return weather
    }

    fun getForeCast() : MutableLiveData<ForeCast>{
        repository.makeApiCall("forecast","Bengaluru", key, object : ResponseListener<ForeCast>{
            override fun onSuccess(value: ForeCast) {
                foreCast.postValue(value)
            }

            override fun onError(error: String) {
                foreCast.postValue(null)
            }

        })

        return foreCast
    }

}