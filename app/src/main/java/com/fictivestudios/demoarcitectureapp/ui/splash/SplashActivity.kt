package com.fictivestudios.demoarcitectureapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.fictivestudios.demoarcitectureapp.R
import com.fictivestudios.demoarcitectureapp.ui.registration.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

lifecycleScope.launch {
    delay(3000)
    val intent = Intent(this@SplashActivity,MainActivity::class.java)
    startActivity(intent)
}


    }
}