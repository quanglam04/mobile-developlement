package com.example.btl.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton Retrofit Client.
 * Chỉ tạo 1 lần duy nhất, dùng chung cho cả app.
 *
 * Cách dùng:
 *   val authApi = RetrofitClient.create(AuthApi::class.java)
 *   val taskApi = RetrofitClient.create(TaskApi::class.java)
 */
object RetrofitClient {

    private const val BASE_URL = "http://localhost:8080/"

    // Logging - hiển thị request/response trong Logcat (chỉ khi debug)
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // OkHttp Client
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    // Retrofit Instance - tạo 1 lần duy nhất
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Tạo API service từ interface.
     * Ví dụ: val authApi = RetrofitClient.create<AuthApi>()
     */
    fun <T> create(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}