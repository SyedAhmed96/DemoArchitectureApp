package com.fictivestudios.demoarcitectureapp.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.fictivestudios.demoarcitectureapp.R
import com.fictivestudios.demoarcitectureapp.databinding.Titlebarbinding
import com.fictivestudios.demoarcitectureapp.ui.registration.MainActivity


class Titlebar : RelativeLayout {
    var binding: Titlebarbinding? = null

    constructor(context: Context) : super(context) {
        initLayout(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initLayout(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initLayout(context)
    }

    fun initLayout(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.titlebar, this, true)
    }

    fun hideTitleBar() {
        resetTitlebar()
    }

    fun resetTitlebar() {
        binding?.rlTitlebarMainLayout?.setVisibility(View.GONE)
    }

    fun setBtnLeft(drawable: Int, listener: OnClickListener?) {
        binding?.ivBack!!.setImageResource(drawable)
        binding?.ivBack!!.setOnClickListener(listener)
    }

    fun setTitle(getActivityContext: MainActivity, title: String) {
        binding?.rlTitlebarMainLayout?.setVisibility(View.VISIBLE)
        binding?.tvTitle?.text = title
        binding?.ivBack?.visibility = View.GONE
        binding?.ivCart?.visibility = View.GONE
        binding?.ivHelp?.visibility = View.VISIBLE
        binding?.ivMenu?.visibility = View.VISIBLE

        binding?.ivMenu?.setOnClickListener {
          //  getActivityContext.openDrawer()
        }

//        binding?.ivHelp?.setOnClickListener {
//            getActivityContext.replaceFragment(
//                HelpFragment(),
//                HelpFragment::class.java.simpleName, true, true
//            )
//        }
    }

    fun setBackTitle(getActivityContext: MainActivity, title: String) {
        binding?.rlTitlebarMainLayout?.setVisibility(View.VISIBLE)
        binding?.tvTitle?.text = title
        binding?.ivBack?.visibility = View.VISIBLE
        binding?.ivHelp?.visibility = View.GONE
        binding?.ivMenu?.visibility = View.GONE
        binding?.ivCart?.visibility = View.GONE

        binding?.ivBack?.setOnClickListener {
            getActivityContext.onBackPressed()
        }
    }

    fun setHideTitle() {
        resetTitlebar()
        binding?.rlTitlebarMainLayout?.setVisibility(View.VISIBLE)
        binding?.ivBack?.visibility = View.INVISIBLE
    }
}