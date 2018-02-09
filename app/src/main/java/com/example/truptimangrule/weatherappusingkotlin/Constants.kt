package com.example.truptimangrule.weatherappusingkotlin

/**
 * Created by trupti.mangrule on 04/01/18.
 */
final class Constants {
    val lat = "latitude"
    val lon = "longitude"

    val WEATHER_URL = "{${lat}},{${lon}}"
    val BASE_URL = "https://api.darksky.net/forecast/ab2381e4da1b89fc98d09484af579b22/"
}