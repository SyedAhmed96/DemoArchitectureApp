package com.fictivestudios.demoarcitectureapp.data.repository

import com.fictivestudios.demoarcitectureapp.data.model.response.user.User
import com.fictivestudios.demoarcitectureapp.data.model.response.user.Users
import com.fictivestudios.demoarcitectureapp.data.network.BaseApiResponse
import com.fictivestudios.demoarcitectureapp.data.network.NetworkResult
import com.fictivestudios.demoarcitectureapp.data.network.Remoteservice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val remoteservice: Remoteservice) : BaseApiResponse() {

    suspend fun getUsers(): Flow<NetworkResult<Users>> {
        return flow<NetworkResult<Users>> {
            emit(safeApiCall { remoteservice.getusers() })
        }.flowOn(Dispatchers.IO)
    }



}