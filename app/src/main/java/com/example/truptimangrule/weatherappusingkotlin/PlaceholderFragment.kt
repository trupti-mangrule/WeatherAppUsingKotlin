package com.example.truptimangrule.weatherappusingkotlin

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.truptimangrule.weatherappusingkotlin.R.string.section_format
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.ApiEndpointInterface
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.RetrofitManager
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.response.Data
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.response.MainResponse
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Array.getDouble
import java.text.SimpleDateFormat
import java.util.*

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {
    var TAG:String="MainActivity_"
    var dailyData: ArrayList<Data>?=null
    var hourlyData: ArrayList<Data>?=null
    var hourlyAdapter :HorizontalAdapter?= null
    var dailyAdapter:VerticalAdapter ?= null
    var lat:Double?=null
    var lon:Double?=null
    var pos:Int?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        //rootView.section_label.text = getString(section_format, arguments.getInt(ARG_SECTION_NUMBER))

        pos=arguments.getInt("position")
        lat=arguments.getDouble("latitude")
        lon=arguments.getDouble("longitude")

        var apiEndpointInterface: ApiEndpointInterface = RetrofitManager.apiService
        //var mainResponse:MainResponse=apiEndpointInterface.getWeather()
        var call: Call<MainResponse>?=apiEndpointInterface.getWeather(lat,lon,"si")
        call?.enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        val mainResponce :MainResponse= response.body()
                        Log.d(TAG, "onResponse: home " + mainResponce.toString())

                        dailyData?.clear()
                        hourlyData?.clear()

                        dailyData=mainResponce.daily?.data as ArrayList<Data>
                        hourlyData=mainResponce.hourly?.data as ArrayList<Data>


                        hourlyAdapter=HorizontalAdapter(hourlyData)
                        dailyAdapter=VerticalAdapter(dailyData)

                        rv_days.adapter=dailyAdapter
                        rv_hourly.adapter=hourlyAdapter
                        val hori_linearLayoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
                        val vert_linearLayoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
                        rv_days.setLayoutManager(vert_linearLayoutManager)

                        //val linearLayoutManager = LinearLayoutManager(this)
                        rv_hourly.setLayoutManager(hori_linearLayoutManager)
                        if(pos==0) {
                            tv_city.setText("Pune")
                            scrollView.setBackgroundColor(Color.GRAY)
                        }else{
                            tv_city.setText("Mumbai")
                            scrollView.setBackgroundColor(Color.LTGRAY)

                        }

                        tv_temp.setText(mainResponce?.currently?.temperature+"\u00B0")

                        tv_sunset.setText("Sunset "+getDate((mainResponce?.daily?.data?.get(0)?.sunsetTime) as Long))
                        tv_sunrise.setText("Sunrise "+getDate((mainResponce?.daily?.data?.get(0)?.sunriseTime) as Long))
                        tv_uv_index.setText("UV Index "+mainResponce?.daily?.data?.get(0)?.uvIndex)



                    }

                } else {
                    Log.d(TAG, "onResponse: code " + response.code() + " message " + response.message())
                }
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.localizedMessage)
                Log.d(TAG, "onFailure: " + t.message)

            }
        })
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
      //  private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(lat: Double,lon:Double,pos:Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putDouble("latitude", lat)
            args.putDouble("longitude", lon)
            args.putInt("position", pos)
            fragment.arguments = args
            return fragment
        }
    }
    fun getDate(milliSeconds: Long): String {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat("hh a")

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliSeconds*1000)
        return formatter.format(calendar.getTime())
    }
}
