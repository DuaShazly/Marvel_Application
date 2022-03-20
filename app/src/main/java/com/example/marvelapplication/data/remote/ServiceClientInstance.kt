package com.example.marvelapplication.data.remote

import com.example.marvelapplication.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceClientInstance {

    companion object {
        private var instance : ServiceClientInstance? = null

        fun getInstance(): ServiceClientInstance {
            if (instance == null)
            {
                instance = ServiceClientInstance()
            }
            return instance!!
        }
    }

    val api: ServiceApi
        get() {
            return retrofit.create(ServiceApi::class.java)
        }

    private val retrofit:Retrofit


    init{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }



}