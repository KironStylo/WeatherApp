package com.kironstylo.weatherApp.data.model.Weather

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("time")
    val dates: List<String>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    @SerializedName("precipitation_probability")
    val precipitationList: List<Int>,
    @SerializedName("relative_humidity_2m")
    val humidityList: List<Int>,
    @SerializedName("wind_speed_10m")
    val windspeedList: List<Double>,
    @SerializedName("weather_code")
    val codes: List<Int>

)
