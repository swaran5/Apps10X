package com.example.apps10x.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.apps10x.R
import com.example.apps10x.databinding.ActivityMainBinding
import com.example.apps10x.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    val context = this
    var days : ArrayList<String> = ArrayList()
    var temp : ArrayList<String> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.isLoading = true
        binding.isError = false

        CoroutineScope(Main).launch {
            loadWeather()
        }

        binding.retry.setOnClickListener {
            binding.isLoading = true
            binding.isError = false
                loadWeather()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadWeather() {
        viewModel.getWeather().observe(context, {

            if(it != null) {
                binding.isLoading = false
                binding.isError = false
                binding.temperature.text = kelvinToCelsius(it.main.temp)
            }else{
                binding.isLoading = false
                binding.isError = true
            }
        })

        viewModel.getForeCast().observe(context, {

            if(it != null){
            binding.isLoading = false
                binding.isError = false
            animateForecastLayout()

            var currentDay = ""
            for(i in it.list.indices){
                if(currentDay == "" || currentDay != stringToDay(it.list[i].dtTxt)){
                    currentDay = stringToDay(it.list[i].dtTxt)
                    days.add(currentDay)
                    temp.add(kelvinToCelsius(it.list[i].main.temp))
                }
            }
            setData(days, temp)
        }else{
                binding.isLoading = false
                binding.isError = true
            }
        })
    }

    private fun setData(days: ArrayList<String>, temp: ArrayList<String>) {

        binding.day1.text = days[0]
        binding.day2.text = days[1]
        binding.day3.text = days[2]
        binding.day4.text = days[3]
        binding.tempDay1.text = temp[0]
        binding.tempDay2.text = temp[1]
        binding.tempDay3.text = temp[2]
        binding.tempDay4.text = temp[3]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    private fun stringToDay(dtTxt: String) : String{
        val s = dtTxt.substring(0, Math.min(dtTxt.length, 10));
        val date = LocalDate.parse(s, DateTimeFormatter.ISO_DATE)
        val day = date.dayOfWeek.toString()
        return day
    }

    private fun animateForecastLayout() {
        forecast_layout.startAnimation(AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.move_up
        ))
    }

    private fun kelvinToCelsius(temp: Double): String {
        val stringTemp = round(temp.toInt() - 273.15, 1)
        return "$stringTemp°C"
    }

    private fun round(value: Double, precision: Int): Double {
        val scale = 10.0.pow(precision.toDouble()).toInt()
        return (value * scale).roundToInt().toDouble() / scale
    }

}