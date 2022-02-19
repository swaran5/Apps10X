package com.example.apps10x.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apps10x.model.WeatherResults
import com.example.apps10x.model.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.apps10x.model.network.ResponseListener

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository : RetroRepository) : ViewModel() {

    private val key = "9b8cb8c7f11c077f8c4e217974d9ee40"
    private var weather = MutableLiveData<WeatherResults>()

//    fun getWeather(): MutableLiveData<WeatherResults> {
//        val call = request.getWeather("Bengaluru", key)
//
//        call.enqueue(object : Callback<WeatherResults> {
//            override fun onResponse(
//                call: Call<WeatherResults>,
//                response: Response<WeatherResults>
//            ) {
//                if(response.isSuccessful){
//                    weather.postValue(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<WeatherResults>, t: Throwable) {
//                print(t.toString())
//            }
//
//        })
//        return weather
//    }

//    fun getWeather() : MutableLiveData<WeatherResults>{
//        repository.makeApiCall("Bengaluru", key, weather)
//        return weather
//    }


    fun getWeather() : MutableLiveData<WeatherResults>{
        repository.makeApiCall("Bengaluru", key, object : ResponseListener<WeatherResults>{
            override fun onSuccess(value: WeatherResults) {
                weather.postValue(value)
            }

            override fun onError(error: String) {
                weather.postValue(null)
            }

        })

        return weather
    }

}