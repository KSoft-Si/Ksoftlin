package si.ksoft.ksoftlin.endpoints

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import si.ksoft.ksoftlin.data.Image
import si.ksoft.ksoftlin.data.RedditPost
import si.ksoft.ksoftlin.data.Tag
import si.ksoft.ksoftlin.data.WikihowImage
import si.ksoft.ksoftlin.dsl.apiUrl

class ImageEndpoint(private val token: String) {
    private val url = "$apiUrl/images"
    private val client = OkHttpClient.Builder().build()

    fun getRandomImage(tag: String, nsfw: Boolean = false): Image {
        val req =
            Request.Builder().url("$url/random-image?tag=$tag&nsfw=$nsfw").header("Authorization", "Bearer $token")
                .build()
        lateinit var data: JsonObject
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Image(
            data["url"].asString,
            data["snowflake"].asString,
            data["nsfw"].asBoolean,
            data["tag"].asString
        )
    }

    fun getTags(): MutableList<String> {
        val list = mutableListOf<String>()
        val req = Request.Builder().url("$url/tags").header("Authorization", "Bearer $token").build()
        lateinit var data: JsonObject
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        for (tag in data["tags"].asJsonArray) {
            list.add(tag.asString)
        }
        return list
    }

    fun isTagNSFW(tag: String): Boolean {
        val req = Request.Builder().url("$url/tags").header("Authorization", "Bearer $token").build()
        lateinit var data: JsonObject
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        for (`object` in data["models"].asJsonArray) {
            if (`object`.asJsonObject["name"].asString == tag) {
                return `object`.asJsonObject["nsfw"].asBoolean
            }
        }
        return false
    }

    fun getNSFWTags(): MutableList<String> {
        val list = mutableListOf<String>()
        val req = Request.Builder().url("$url/tags").header("Authorization", "Bearer $token").build()
        lateinit var data: JsonObject
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        for (tag in data["nsfw_tags"].asJsonArray) {
            list.add(tag.asString)
        }
        return list
    }

    fun searchTag(search: String): Tag {
        val req = Request.Builder().url("$url/tags/$search").header("Authorization", "Bearer $token").build()
        lateinit var data: JsonObject
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val list = mutableListOf<String>()
        for (d in data["tags"].asJsonArray) {
            list.add(d.asString)
        }
        return Tag(
            data["models"].asJsonArray.asJsonObject["name"].asString,
            data["models"].asJsonArray.asJsonObject["nsfw"].asBoolean,
            list
        )
    }

    fun getImage(snowflake: String): Image {
        val req = Request.Builder().url("$url/image/$snowflake").header("Authorization", "Bearer $token").build()
        lateinit var data: JsonObject
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Image(
            data["url"].asString,
            data["snowflake"].asString,
            data["nsfw"].asBoolean,
            data["tag"].asString
        )
    }

    fun getRandomMeme(): RedditPost = getRedditPost("random-meme")

    fun getRandomCutePicture(): RedditPost = getRedditPost("random-aww")

    fun getRandomNSFW(gifs: Boolean = false): RedditPost = getRedditPost("random-nsfw?gifs=$gifs")

    fun getWikihowImage(nsfw: Boolean = false): WikihowImage {
        val req =
            Request.Builder().url("$url/images/random-wikihow?nsfw=$nsfw").header("Authorization", "Bearer $token")
                .build()
        lateinit var data: JsonObject
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return WikihowImage(
            data["url"].asString,
            data["title"].asString,
            data["nsfw"].asBoolean,
            data["articleUrl"].asString
        )
    }

    fun getRandomImageFromSubReddit(subReddit: String, removeNsfw: Boolean = true, span: String = "day"): RedditPost {
        val req = Request.Builder().url("$url/rand-reddit/$subReddit?remove_nsfw=$removeNsfw&span=$span")
            .header("Authorization", "Bearer $token").build()
        lateinit var data: JsonObject
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return RedditPost(
            data["title"].asString,
            data["image_url"].asString,
            data["source"].asString,
            data["subreddit"].asString,
            data["upvotes"].asInt,
            data["downvotes"].asInt,
            data["comments"].asInt,
            data["created_at"].asLong,
            data["nsfw"].asBoolean,
            data["author"].asString
        )
    }

    private fun getRedditPost(endpoint: String): RedditPost {
        val req = Request.Builder().url("$url/$endpoint").header("Authorization", "Bearer $token").build()
        lateinit var data: JsonObject
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return RedditPost(
            data["title"].asString,
            data["image_url"].asString,
            data["source"].asString,
            data["subreddit"].asString,
            data["upvotes"].asInt,
            data["downvotes"].asInt,
            data["comments"].asInt,
            data["created_at"].asLong,
            data["nsfw"].asBoolean,
            data["author"].asString
        )
    }
}


