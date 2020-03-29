package si.ksoft.ksoftlin.data.kumo.weather.easy

import com.google.gson.annotations.SerializedName


data class Location(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    val address: String
)

data class Alert(
    val title: String,
    val regions: List<String>,
    val severity: String,
    val time: String,
    val expires: String,
    val description: String,
    val uri: String
)

interface WeatherResponse {
    val error: Boolean
    val code: Int
    val message: String?
}

data class WeatherDailyResponse(
    val data: WeatherDailyData,
    override val error: Boolean,
    override val code: Int,
    override val message: String?
) : WeatherResponse

data class WeatherDailyData(
    val summary: String,
    val icon: String, @SerializedName("icon_url") val iconUrl: String,
    val alerts: List<Alert>, @SerializedName("by_day") val byDay: List<WeatherDailyByDayData>
)

data class WeatherDailyByDayData(
    val time: String,
    val summer: String,
    val icon: String,
    val sunriseTime: String,
    val sunsetTime: String,
    val moonPhase: Double,
    val precipIntensity: Double,
    val precipIntensityMax: Double,
    val precipIntensityMaxTime: Int,
    val precipProbability: Double,
    val precipType: String,
    val temperatureHigh: Double,
    val temperatureHighTime: Int,
    val temperatureLow: Double,
    val temperatureLowTime: Int,
    val apparentTemperatureHigh: Double,
    val apparentTemperatureHighTime: Int,
    val apparentTemperatureLow: Double,
    val apparentTemperatureLowTime: Int,
    val dewPoint: Double,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double,
    val windGust: Double,
    val windGustTime: Int,
    val windBearing: Int,
    val cloudCover: Double,
    val uvIndex: Int,
    val uvIndexTime: Int,
    val visibility: Double,
    val ozone: Double,
    val temperatureMin: Double,
    val temperatureMinTime: Int,
    val temperatureMax: Double,
    val temperatureMaxTime: Int,
    val apparentTemperatureMin: Double,
    val apparentTemperatureMinTime: Int,
    val apparentTemperatureMax: Double,
    val apparentTemperatureMaxTime: Int,
    @SerializedName("icon_url")
    val iconUrl: String
)

data class WeatherCurrentlyResponse(
    val error: Boolean,
    val status: Int, @SerializedName("data") val weather: WeatherCurrently,
    val message: String
)

data class WeatherCurrently(
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
    val alerts: List<Alert>,
    val units: String,
    val location: Location
)

data class WeatherMinutelyResponse(
    override val error: Boolean,
    override val code: Int,
    override val message: String?,
    val data: WeatherMinutelyData
) : WeatherResponse

data class WeatherMinutelyData(
    val summary: String,
    val icon: String, @SerializedName("icon_url") val iconUrl: String,
    val alerts: List<Alert>,
    val units: String,
    val location: Location, @SerializedName("by_minute") val byMinute: List<WeatherMinutelyByMinuteData>
)

data class WeatherMinutelyByMinuteData(
    val time: String,
    val precipIntensity: Int,
    val precipProbability: Int,
    val sunriseTime: String,
    val sunsetTime: String, @SerializedName("icon_url") val iconUrl: String
)

data class WeatherHourlyResponse(
    override val error: Boolean,
    override val code: Int,
    override val message: String?,
    val data: WeatherHourlyData
) : WeatherResponse

data class WeatherHourlyData(
    val summary: String,
    val icon: String, @SerializedName("icon_url") val iconUrl: String,
    val alerts: List<Alert>,
    val units: String,
    val location: Location, @SerializedName("by_hour") val byHour: List<WeatherHourlyByHourData>
)

data class WeatherHourlyByHourData(
    val time: String,
    val summary: String,
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
    val iconUrl: String
)