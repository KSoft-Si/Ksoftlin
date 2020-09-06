package si.ksoft.ksoftlin.endpoints

import io.ktor.client.request.*
import si.ksoft.ksoftlin.builder.client
import si.ksoft.ksoftlin.data.kumo.currency.CurrencyResponse
import si.ksoft.ksoftlin.data.kumo.location.LocationResponse
import si.ksoft.ksoftlin.data.kumo.location.geoip.GeoIPLocationResponse
import si.ksoft.ksoftlin.data.kumo.weather.easy.WeatherCurrentlyResponse
import si.ksoft.ksoftlin.data.kumo.weather.easy.WeatherDailyResponse
import si.ksoft.ksoftlin.data.kumo.weather.easy.WeatherHourlyResponse
import si.ksoft.ksoftlin.data.kumo.weather.easy.WeatherMinutelyResponse
import si.ksoft.ksoftlin.util.api

/**
 * The class containing HTTP Calls to the KSoft.Si Kumo Endpoint.
 *
 * @since 2.0
 * @author chachy
 */

class KumoEndpoint(private val token: String) {
    suspend fun getLocation(
        location: String,
        isFast: Boolean = true,
        isMore: Boolean = false,
        mapZoom: Int = 12,
        includeMap: Boolean = false
    ) = client.get<LocationResponse>("$api/kumo/gis") {
        header("Authorization", token)
        parameter("q", location)
        parameter("fast", isFast)
        parameter("more", isMore)
        parameter("map_zoom", mapZoom)
        parameter("includeMap", includeMap)
    }

    suspend fun getWeatherCurrently(
        location: String,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherCurrentlyResponse>("$api/kumo/weather/currently") {
        header("Authorization", token)
        parameter("q", location)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }

    suspend fun getWeatherMinutely(
        location: String,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherMinutelyResponse>("$api/kumo/weather/minutely") {
        header("Authorization", token)
        parameter("q", location)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }

    suspend fun getWeatherHourly(
        location: String,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherHourlyResponse>("$api/kumo/weather/hourly") {
        header("Authorization", token)
        parameter("q", location)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }

    suspend fun getWeatherDaily(
        location: String,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherDailyResponse>("$api/kumo/weather/daily") {
        header("Authorization", token)
        parameter("q", location)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }

    suspend fun getWeatherWithLongitudeAndLatitudeCurrently(
        longitude: Double,
        latitude: Double,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherCurrentlyResponse>("$api/kumo/weather/$latitude,$longitude/currently") {
        header("Authorization", token)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }

    suspend fun getWeatherWithLongitudeAndLatitudeMinutely(
        longitude: Double,
        latitude: Double,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherMinutelyResponse>("$api/kumo/weather/$latitude,$longitude/minutely}") {
        header("Authorization", token)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }

    suspend fun getWeatherWithLongitudeAndLatitudeHourly(
        longitude: Double,
        latitude: Double,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherHourlyResponse>("$api/kumo/weather/$latitude,$longitude/hourly") {
        header("Authorization", token)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }

    suspend fun getWeatherWithLongitudeAndLatitudeDaily(
        longitude: Double,
        latitude: Double,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherDailyResponse>("$api/kumo/weather/$latitude,$longitude/daily") {
        header("Authorization", token)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }


    suspend fun getLocationDataFromIP(ip: String) = client.get<GeoIPLocationResponse>("$api/kumo/geoip") {
        header("Authorization", token)
        parameter("ip", ip)
    }

    suspend fun convertCurrency(fromCurrency: String, toCurrency: String, value: String) =
        client.get<CurrencyResponse>("$api/kumo/currency") {
            header("Authorization", token)
            parameter("from", fromCurrency)
            parameter("to", toCurrency)
            parameter("value", value)
        }
}
