package com.fictivestudios.demoarcitectureapp.data.network


import com.fictivestudios.demoarcitectureapp.data.model.response.login.LoginResponse
import com.fictivestudios.demoarcitectureapp.data.model.response.user.User
import com.fictivestudios.demoarcitectureapp.data.model.response.user.Users
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET(USERS)
    suspend fun getUsers(): Response<Users>

    @POST(LOGIN)
    suspend fun login(@Body requestBody: RequestBody):Response<LoginResponse>

}