package si.ksoft.ksoftlin.data.image

data class TagResponse(val name: String)

data class ImageResponse(val url: String, val snowflake: String, val nsfw: Boolean, val tag: TagResponse)