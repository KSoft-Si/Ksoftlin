package si.ksoft.ksoftlin.data.kumo.weather.easy

import com.google.gson.annotations.SerializedName

data class Weather(
    val time: String,
    val summary: String,
    val icon: String,
    val precipIntensity: Int,
    val precipProbability: Int,
    val temperature: Double,
    val apparentTemperature: Double,
    val dewPoint: Double,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double,
    val windGust: Double,
    val windBearing: Int,
    val cloudCover: Double,
    val uvIndex: Int,
    val visibility: Double,
    val ozone: Double,
    val sunriseTime: String,
    val sunsetTime: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    val alerts: MutableList<Alert>,
    val units: String,
    val location: Location
)

data class Location(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    val address: String
)

data class Alert(
    val title: String,
    val regions: MutableList<String>,
    val severity: String,
    val time: String,
    val expires: String,
    val description: String,
    val uri: String
)

data class WeatherResponse(val error: Boolean, val status: Int, @SerializedName("data") val weather: Weather, val message: String? = null)