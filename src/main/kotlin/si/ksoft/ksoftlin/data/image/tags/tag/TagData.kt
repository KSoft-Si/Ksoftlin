package si.ksoft.ksoftlin.data.image.tags.tag

import com.google.gson.annotations.SerializedName

data class Tag(val name: String, @SerializedName("nsfw") val isNsfw: Boolean)

data class TagResponse(val models: MutableList<Tag>)