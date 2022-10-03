package com.fictivestudios.demoarcitectureapp.ui.registration

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.fictivestudios.demoarcitectureapp.R
import com.fictivestudios.demoarcitectureapp.base.BaseActivity
import com.fictivestudios.demoarcitectureapp.databinding.MainActivityBinding
import com.fictivestudios.demoarcitectureapp.utils.Titlebar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity(), View.OnClickListener {
    var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        hideBttomBar()
        mainHideTitle()

        setListener()

    }

    override fun setMainFrameLayoutID() {

    }

    fun setListener() {
        binding?.helpLayout?.setOnClickListener(this)
        binding?.aboutLayout?.setOnClickListener(this)
        binding?.llAbout?.setOnClickListener(this)
        binding?.llHelp?.setOnClickListener(this)
    }

    fun getTitlebar(): Titlebar {
        return binding!!.titlebar
    }

    fun hideBttomBar() {
        binding?.llBotomBar?.visibility = View.GONE
    }

    fun showBttomBar() {
        binding?.llBotomBar?.visibility = View.VISIBLE
    }

//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount > 1) {


//        }
//        else {
//            val exitDialog = ExitDialog()
//            exitDialog.show(supportFragmentManager, "exitDialog")
//        }
//    }

    fun mainHideTitle() {
        binding!!.titlebar.visibility = View.GONE
    }

    fun mainShowTitle() {
        binding!!.titlebar.visibility = View.VISIBLE
    }


    fun unlockMenu() {
        binding!!.drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    fun lockMenu() {
        binding!!.drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun openDrawer() {
        binding!!.drawerlayout.openDrawer(GravityCompat.START)
    }

    fun closeDrawers() {
        binding!!.drawerlayout.closeDrawers()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.aboutLayout -> {
                // replaceFragment(AboutFragment(), AboutFragment::class.java.simpleName, true, true)
            }

            R.id.helpLayout -> {
                //  replaceFragment(HelpFragment(), HelpFragment::class.java.simpleName, true, true)
            }

            R.id.llAbout -> {
                // replaceFragment(AboutFragment(), AboutFragment::class.java.simpleName, true, true)
            }

            R.id.llHelp -> {
                // replaceFragment(HelpFragment(), HelpFragment::class.java.simpleName, true, true)
            }
        }
    }
}