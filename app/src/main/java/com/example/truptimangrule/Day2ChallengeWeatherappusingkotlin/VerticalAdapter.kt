package com.example.truptimangrule.weatherappusingkotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.truptimangrule.Day2ChallengeWeatherappusingkotlin.R
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.response.Data
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by trupti.mangrule on 05/01/18.
 */
class VerticalAdapter(val dailyList: ArrayList<Data>?) : RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: VerticalAdapter.ViewHolder, position: Int) {
        println("VerticalAdapter "+dailyList?.get(position)?.time as Long)
        val text:String=getDate(dailyList?.get(position)?.time as Long)
        println("VerticalAdapter text "+text.subSequence(0,2))
        holder.tv_day.setText(text)
        // holder.tv_day.setText(dailyList?.get(position)?.time)
        holder.tv_max.setText(dailyList?.get(position)?.temperatureMax?.substring(0,2)+"\u00B0")
        holder.tv_min.setText(dailyList?.get(position)?.temperatureMin?.substring(0,2)+"\u00B0")

        if(dailyList?.get(position)?.icon=="clear-day"){
            holder.iv_image.setBackgroundResource(R.drawable.clear_day)
        }else if(dailyList?.get(position)?.icon=="clear-night"){
            holder.iv_image.setBackgroundResource(R.drawable.clear_night)
        }else if(dailyList?.get(position)?.icon=="partly-cloudy-day"){
            holder.iv_image.setBackgroundResource(R.drawable.partly_cloudy_day)
        }else if(dailyList?.get(position)?.icon=="partly-cloudy-night"){
            holder.iv_image.setBackgroundResource(R.drawable.partly_cloudy_night)
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        if (dailyList != null) {
            return dailyList.size
        }
        return 0
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_day: TextView =itemView.findViewById(R.id.tv_day)
        var iv_image: ImageView =itemView.findViewById(R.id.iv_image)
        var tv_max: TextView =itemView.findViewById(R.id.tv_max)
        var tv_min: TextView =itemView.findViewById(R.id.tv_min)
    }
    fun getDate(milliSeconds: Long): String {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat("EEEE")

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliSeconds*1000)
        return formatter.format(calendar.getTime())
    }
}
