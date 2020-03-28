package si.ksoft.ksoftlin.data.image

data class Tag(val name: String)

data class Image(val url: String, val snowflake: String, val nsfw: Boolean, val tag: Tag)