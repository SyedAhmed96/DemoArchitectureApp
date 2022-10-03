package com.fictivestudios.demoarcitectureapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fictivestudios.demoarcitectureapp.R

abstract class BaseActivity : AppCompatActivity() {
    var baseFragment: BaseFragment? = null
    var mainFrameLayoutID: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    abstract fun setMainFrameLayoutID()

}