package com.fictivestudios.demoarcitectureapp.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.fictivestudios.demoarcitectureapp.ui.registration.MainActivity
import com.fictivestudios.demoarcitectureapp.utils.Titlebar

abstract class BaseFragment : Fragment() {
    var getActivityContext: MainActivity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    abstract fun setTitlebar(titlebar: Titlebar)

    fun getActivityContext(): MainActivity? {
        return getActivityContext
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            val contex = context as MainActivity?
            if (contex != null)
                getActivityContext = context
        }
    }

    override fun onResume() {
        super.onResume()
        if (getActivityContext != null) {
            setTitlebar(getActivityContext!!.getTitlebar())
        }
    }
}