package com.example.truptimangrule.weatherappusingkotlin


import android.support.v7.app.AppCompatActivity
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import com.example.truptimangrule.weatherappusingkotlin.R.string.section_format
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.ApiEndpointInterface
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.RetrofitManager
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.response.Data
import com.example.truptimangrule.weatherappusingkotlin.network.retrofit.response.MainResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.item_horizontal.*
import kotlinx.android.synthetic.main.item_vertical.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter]
     */

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    internal var provider: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.setTitle("Weather App")
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter
    }
}
