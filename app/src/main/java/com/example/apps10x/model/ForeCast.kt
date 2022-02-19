package com.example.apps10x.model

import com.google.gson.annotations.SerializedName

data class ForeCast (
    val cod: String,
    val message: Long,
    val cnt: Long,
    val list: List<ListElement>,
    val city: City
)

data class City (
    val id: Long,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Long,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
)

data class ListElement (
    val dt: Long,
    val main: MainClass,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop: Long,
    val sys: Sys,

    @SerializedName("dt_txt")
    val dtTxt: String
)

data class MainClass (
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("temp_max")
    val tempMax: Double,

    val pressure: Long,

    @SerializedName("sea_level")
    val seaLevel: Long,

    @SerializedName("grnd_level")
    val grndLevel: Long,

    val humidity: Long,

    @SerializedName("temp_kf")
    val tempKf: Double
)

enum class Pod(val value: String) {
    D("d"),
    N("n");

    companion object {
        public fun fromValue(value: String): Pod = when (value) {
            "d"  -> D
            "n"  -> N
            else -> throw IllegalArgumentException()
        }
    }
}

enum class Description(val value: String) {
    BrokenClouds("broken clouds"),
    ClearSky("clear sky"),
    FewClouds("few clouds"),
    OvercastClouds("overcast clouds"),
    ScatteredClouds("scattered clouds");

    companion object {
        public fun fromValue(value: String): Description = when (value) {
            "broken clouds"    -> BrokenClouds
            "clear sky"        -> ClearSky
            "few clouds"       -> FewClouds
            "overcast clouds"  -> OvercastClouds
            "scattered clouds" -> ScatteredClouds
            else               -> throw IllegalArgumentException()
        }
    }
}
