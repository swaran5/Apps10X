package com.example.apps10x.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.example.apps10x.R
import com.example.apps10x.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        forecast_layout.startAnimation(AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.move_up
        ))

        viewModel.getWeather().observe(this, {
            val temp = it.main.temp
            val tem = it.main.feelsLike
        })

    }
}