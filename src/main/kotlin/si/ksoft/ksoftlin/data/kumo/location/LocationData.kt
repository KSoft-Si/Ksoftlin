package si.ksoft.ksoftlin.data.kumo.location

import com.google.gson.annotations.SerializedName

data class Location(
    val address: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    @SerializedName("bounding_box")
    val boundingBox: List<String>,
    val type: List<String>,
    val map: String
)

data class LocationResponse(val error: Boolean, val code: Int, val message: String? = null, val data: Location)