package com.example.apps10x.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.apps10x.R
import com.example.apps10x.databinding.ActivityMainBinding
import com.example.apps10x.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.isLoading = true

        CoroutineScope(Main).launch {
            print("inside launch")

            viewModel.getWeather().observe(context, {

                animateForecastLayout()
                binding.temperature.text = kelvinToCelsius(it.main.temp)
                binding.isLoading = false

            })

        }

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