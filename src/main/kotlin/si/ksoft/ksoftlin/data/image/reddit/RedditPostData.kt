package si.ksoft.ksoftlin.data.image.reddit

import com.google.gson.annotations.SerializedName

data class RedditPost(
    val title: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("source")
    val postLink: String,
    @SerializedName("subreddit")
    val subReddit: String,
    @SerializedName("upvotes")
    val upVotes: Int,
    @SerializedName("downvotes")
    val downVotes: Int,
    val comments: Int,
    val createdAt: Long,
    @SerializedName("nsfw")
    val isNsfw: Boolean,
    val author: String
)