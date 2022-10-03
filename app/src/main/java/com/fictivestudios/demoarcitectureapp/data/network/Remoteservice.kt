package com.fictivestudios.demoarcitectureapp.data.network

import okhttp3.RequestBody
import retrofit2.http.Body
import javax.inject.Inject

class Remoteservice @Inject constructor (private val apiService: ApiService){

   suspend fun getusers() = apiService.getUsers()

   suspend fun login(requestBody: RequestBody) = apiService.login(requestBody)

}