package si.ksoft.ksoftlin.data.kumo.location.geoip

import com.google.gson.annotations.SerializedName

data class LocationLinks(
    val weather: String,
    val gis: String, @SerializedName("openstreetmap") val openStreetMap: String, @SerializedName("googlemaps") val googleMaps: String
)

data class Location(
    val city: String,
    @SerializedName("continent_code")
    val continentCode: String,
    @SerializedName("continent_name")
    val continentName: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("dma_code")
    val dmaCode: String,
    @SerializedName("is_in_european_union")
    val isInEuropeanUnion: Boolean,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("postal_code")
    val postalCode: String,
    val region: String,
    @SerializedName("time_zone")
    val timeZone: String,
    val apis: LocationLinks
)

data class GeoIPLocationResponse(val error: Boolean, val code: Int, @SerializedName("data") val location: Location, val message: String? = null)