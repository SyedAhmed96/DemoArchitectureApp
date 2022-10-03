package com.fictivestudios.demoarcitectureapp.ui.fragment.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercemvvmpractice2.utilities.extensions.showToast
import com.fictivestudios.demoarcitectureapp.data.model.response.login.LoginResponse
import com.fictivestudios.demoarcitectureapp.data.network.NetworkHelper
import com.fictivestudios.demoarcitectureapp.data.network.NetworkResult
import com.fictivestudios.demoarcitectureapp.data.repository.LoginRepositorey
import com.fictivestudios.demoarcitectureapp.utils.Constant.Companion.TOKEN
import com.fictivestudios.demoarcitectureapp.utils.db.UserPreferences
import com.fictivestudios.demoarcitectureapp.utils.extensions.getJsonRequestBody
import com.fictivestudios.demoarcitectureapp.utils.extensions.isNotValidEmail
import com.fictivestudios.demoarcitectureapp.utils.extensions.isNotValidPassword
import com.fictivestudios.demoarcitectureapp.utils.writeString
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val networkHelper: NetworkHelper,
    val loginRepo: LoginRepositorey,
    val userPreferences: UserPreferences,
    @ApplicationContext context: Context

) : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val context: Context? = context

    private val _users: MutableLiveData<NetworkResult<LoginResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<LoginResponse>> = _users

    fun validation(): Boolean {

//        if (email.toString().isNotValidEmail()) {
//            if (context != null) {
//                showToast("Not Valid Email", context)
//                Log.d("email", "Not Valid Email")
//            }
//
//            return false
//        } else

            if (email.value?.length!! >= 0) {
            if (context != null) {
                showToast("Email not empty", context)
                Log.d("email", "Email not empty")
            }
            return false
        }
//
//            else if (password.toString().isNotValidPassword()) {
//            if (context != null) {
//                showToast("Password is not valid", context)
//                Log.d("pass", "Password is not valid")
//            }
//            return false
//        }
//

            else if (password.value?.length!! >= 0) {
            if (context != null) {
                showToast("Password not empty", context)
                Log.d("pass", "Password not empty")
            }
            return false
        } else {

            return true
                login()
        }

    }


    fun login() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                Log.d("pass", email.value.toString() + " " + password.value.toString())
                loginRepo.login(
                    JSONObject().apply {
                        put("username", email.value.toString())
                        put("password", password.value.toString())
                    }.toString().getJsonRequestBody()
                )
                    .collect { values ->

                        _users.value = values

                        userPreferences.saveUserData(_users.value!!.data?.email!!,_users.value!!.data?.firstName!!,_users.value!!.data?.gender!!,
                            _users.value!!.data?.id!!,_users.value!!.data?.image!!,_users.value!!.data?.lastName!!,_users.value!!.data?.username!!,
                            _users.value!!.data?.token!!)

//                        if (context != null) {
//                            saveToken(_users.value?.data?.token.toString())
//                            Log.d("token", _users.value?.data?.token.toString())
//                        }


                    }
            }


        }
        else {

            _users.postValue(NetworkResult.Error("No internet connection", null))
        }


    }

    fun saveToken(token: String) {

        viewModelScope.launch(Dispatchers.IO) {

            context?.writeString(TOKEN, token)

        }

    }


}