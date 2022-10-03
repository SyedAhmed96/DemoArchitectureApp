package com.fictivestudios.demoarcitectureapp.data.repository


import com.fictivestudios.demoarcitectureapp.data.model.response.login.LoginResponse
import com.fictivestudios.demoarcitectureapp.data.network.BaseApiResponse
import com.fictivestudios.demoarcitectureapp.data.network.NetworkResult
import com.fictivestudios.demoarcitectureapp.data.network.Remoteservice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody
import javax.inject.Inject

class LoginRepositorey @Inject constructor(private val remoteservice: Remoteservice) : BaseApiResponse()  {

    suspend fun login(requestBody: RequestBody): Flow<NetworkResult<LoginResponse>> {

        return flow<NetworkResult<LoginResponse>> {
            emit(safeApiCall {
                remoteservice.login(requestBody)
            })

        }


    }

}