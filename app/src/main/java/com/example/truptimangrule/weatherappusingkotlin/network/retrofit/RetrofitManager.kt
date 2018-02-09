package com.example.truptimangrule.weatherappusingkotlin.network.retrofit


/**
 * Created by trupti.mangrule on 04/01/18.
 */


import com.example.truptimangrule.weatherappusingkotlin.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitManager {

    private val TAG = "RetrofitManager"
    private var retrofit: Retrofit ?= null
    private val logging = HttpLoggingInterceptor()
    private val httpClient = OkHttpClient.Builder()




    //to handle null pointer exception of objects.. though its not working...!!!
    val client: Retrofit
        get() {
            if (retrofit == null) {
                val gson = GsonBuilder().serializeNulls().create()
                httpClient.addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
                retrofit = Retrofit.Builder()
                        .client(httpClient.build())
                        .baseUrl("https://api.darksky.net/forecast/ab2381e4da1b89fc98d09484af579b22/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }

            return retrofit as Retrofit
        }


    val apiService: ApiEndpointInterface
        get() = client.create(ApiEndpointInterface::class.java)

}
