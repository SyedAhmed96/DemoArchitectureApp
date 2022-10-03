package com.fictivestudios.demoarcitectureapp.ui.fragment.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fictivestudios.demoarcitectureapp.data.model.response.user.Users
import com.fictivestudios.demoarcitectureapp.data.network.NetworkHelper
import com.fictivestudios.demoarcitectureapp.data.network.NetworkResult
import com.fictivestudios.demoarcitectureapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper,

    ) : ViewModel() {

    private val _users: MutableLiveData<NetworkResult<Users>> = MutableLiveData()
    val response: LiveData<NetworkResult<Users>> = _users

    init {
        fetchUsers()
    }


    @SuppressLint("NullSafeMutableLiveData")
    fun fetchUsers() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {

                mainRepository.getUsers().collect { values ->

                    _users.value = values

                }

            }
        } else {

            _users.postValue(NetworkResult.Error("No internet connection", null))
        }


    }

}