package com.example.truptimangrule.weatherappusingkotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by trupti.mangrule on 05/01/18.
 */
class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        var lat:Double?=null
        var lon:Double?=null
        if(position==0){

            lat=18.5204
            lon=73.8567
        }else{
            lat=19.0760
            lon=72.8777
        }
        return PlaceholderFragment.newInstance(lat,lon,position)
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}