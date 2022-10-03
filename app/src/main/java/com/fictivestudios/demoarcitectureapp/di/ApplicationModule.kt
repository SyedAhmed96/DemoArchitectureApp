package com.fictivestudios.demoarcitectureapp.di

import androidx.multidex.BuildConfig
import com.fictivestudios.demoarcitectureapp.data.network.ApiService
import com.fictivestudios.demoarcitectureapp.data.network.BASE_URL
import com.fictivestudios.demoarcitectureapp.data.network.requestTimeoutInSeconds
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {
    @Provides
    fun providesBaseUrl(): String = BASE_URL

    @Provides
    @Singleton
    fun providesHttpLogger(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesGsonFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun providesNetworkInterceptor(): Request {
        return Request.Builder()
            .header("platform", "android")
            .header("app-version", BuildConfig.BUILD_TYPE)

            .build()
    }

    @Provides
    fun providesNetworkingTimeout(): Long = requestTimeoutInSeconds


    @Provides
    @Singleton
    fun providesOkHttpClient(timeOutInSeconds: Long, interceptor:
    HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            .writeTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            .connectTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            .addInterceptor{chain ->
                val original = chain.request()
                val request = original.newBuilder()
//                    .header("platform", "android")
//                    .header("app-version", BuildConfig.BUILD_TYPE)
                    //  .header("Authorization", NetworkConstants.basicAuth)
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(interceptor)

            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(baseUrl: String, gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)







}