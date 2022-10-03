package com.fictivestudios.demoarcitectureapp.ui.fragment.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommercemvvmpractice2.utilities.extensions.showToast
import com.fictivestudios.demoarcitectureapp.R
import com.fictivestudios.demoarcitectureapp.base.BaseFragment
import com.fictivestudios.demoarcitectureapp.data.network.NetworkResult
import com.fictivestudios.demoarcitectureapp.databinding.LoginBinding
import com.fictivestudios.demoarcitectureapp.utils.Titlebar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private lateinit var loginBinding: LoginBinding
    private val loginViewModel: LoginViewModel by viewModels()


    override fun setTitlebar(titlebar: Titlebar) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = LoginBinding.inflate(inflater, container, false)
        loginBinding.loginViewModel = loginViewModel
        loginBinding.lifecycleOwner = this

          setObserver()


        loginBinding.siginpasssow.setOnClickListener {

            showPassword()

        }

        return loginBinding.root
    }


    fun setObserver() {

        loginViewModel.response.observe(getActivityContext!!) { response ->
            when (response) {

                is NetworkResult.Success -> {
                    loginBinding.progressBar.visibility = View.GONE

                    try {
                        val action = LoginFragmentDirections.actionLoginFragmentToHome3()
                        findNavController().navigate(action)
                    } catch (e: Exception) {
                        e.printStackTrace()

                    }


                }
                is NetworkResult.Error -> {
                    loginBinding.progressBar.visibility = View.GONE
                    showToast(response.message.toString(), getActivityContext)
                }
                is NetworkResult.Loading -> {
                    loginBinding.progressBar.visibility = View.VISIBLE

                }

            }
        }

    }


    fun showPassword(){

        if(loginBinding.password.transformationMethod.equals(PasswordTransformationMethod.getInstance())){
            loginBinding.siginpasssow.setImageResource(R.drawable.visibility_off)
            loginBinding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        }
        else{
            loginBinding.siginpasssow.setImageResource(R.drawable.visibility)

            //Hide Password
            loginBinding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());

        }
    }



}