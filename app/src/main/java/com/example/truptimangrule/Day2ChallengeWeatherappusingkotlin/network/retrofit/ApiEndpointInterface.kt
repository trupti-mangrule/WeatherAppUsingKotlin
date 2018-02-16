package com.example.truptimangrule.weatherappusingkotlin.network.retrofit

import android.provider.SyncStateContract
import com.example.truptimangrule.weatherappusingkotlin.Constants
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.response.MainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by trupti.mangrule on 04/01/18.
 */
interface ApiEndpointInterface {


    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter
    @GET("{${"latitude"}},{${"longitude"}}")
    fun getWeather(@Path("latitude") lat: Double?, @Path("longitude") lon: Double?, @Query("units") unit: String): Call<MainResponse>


}
