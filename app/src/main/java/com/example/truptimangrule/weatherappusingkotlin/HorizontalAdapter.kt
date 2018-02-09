package com.example.truptimangrule.weatherappusingkotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.response.Data
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by trupti.mangrule on 05/01/18.
 */
class HorizontalAdapter(val hourlyList: ArrayList<Data>?) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: HorizontalAdapter.ViewHolder, position: Int) {
        println("HorizontalAdapter "+hourlyList?.get(position)?.time as Long)

        holder.tv_temp.setText(hourlyList?.get(position)?.temperature?.substring(0,2)+"\u00B0")
        println("temp image should be "+hourlyList?.get(position)?.icon)

//clear-day
//clear-night
//partly-cloudy-day
//partly-cloudy-night

        if(hourlyList?.get(position)?.icon=="clear-day"){
            holder.iv_temp_image.setBackgroundResource(R.drawable.clear_day)
        }else if(hourlyList?.get(position)?.icon=="clear-night"){
            holder.iv_temp_image.setBackgroundResource(R.drawable.clear_night)
        }else if(hourlyList?.get(position)?.icon=="partly-cloudy-day"){
            holder.iv_temp_image.setBackgroundResource(R.drawable.partly_cloudy_day)
        }else if(hourlyList?.get(position)?.icon=="partly-cloudy-night"){
            holder.iv_temp_image.setBackgroundResource(R.drawable.partly_cloudy_night)
        }
        holder.iv_temp_image.setBackgroundResource(R.drawable.rain)
        val text:String=getDate(hourlyList?.get(position)?.time as Long)
        println("HorizontalAdapter text "+text)
        holder.tv_time.setText(text)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        if (hourlyList != null) {
            return hourlyList.size
        }
        return 0
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_time: TextView =itemView.findViewById(R.id.tv_time)
        var iv_temp_image: ImageView =itemView.findViewById(R.id.iv_temp_image)
        var tv_temp: TextView =itemView.findViewById(R.id.tv_temp_rv)
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