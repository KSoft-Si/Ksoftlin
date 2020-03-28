package si.ksoft.ksoftlin.data.image.wikihow

import com.google.gson.annotations.SerializedName

data class WikiHow(val url: String, val title: String, @SerializedName("nsfw") val isNsfw: Boolean, @SerializedName("article_url") val articleUrl: String)