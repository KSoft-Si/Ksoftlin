package si.ksoft.ksoftlin.endpoints

import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import si.ksoft.ksoftlin.builder.client
import si.ksoft.ksoftlin.data.image.ImageResponse
import si.ksoft.ksoftlin.data.image.reddit.RedditPost
import si.ksoft.ksoftlin.data.image.tags.tag.TagResponse
import si.ksoft.ksoftlin.data.image.tags.tag.search.SearchTagResponse
import si.ksoft.ksoftlin.data.image.wikihow.WikiHowResponse
import si.ksoft.ksoftlin.util.api

/**
 * The class containing HTTP Calls to the KSoft.Si Image Endpoint.
 *
 * @since 2.0
 * @author chachy
 */

class ImageEndpoint(private val token: String) {
    suspend fun getRandomImage(tag: String, isNsfw: Boolean = false) = client.get<ImageResponse>("$api/images/random-image") {
        parameter("tag", tag)
        parameter("nsfw", isNsfw)
        header("Authorization", token)
    }

    suspend fun getTags() = client.get<TagResponse>("$api/images/tags") { header("Authorization", token) }

    suspend fun searchTag(tag: String) =
        client.get<SearchTagResponse>("$api/images/tags/$tag") { header("Authorization", token) }

    suspend fun getImageFromSnowflake(snowflake: String) =
        client.get<ImageResponse>("$api/images/image/$snowflake") { header("Authorization", token) }

    suspend fun getRandomMeme() = client.get<RedditPost>("$api/images/random-meme") { header("Authorization", token) }

    suspend fun getRandomWikiHow(isNsfw: Boolean = false) = client.get<WikiHowResponse>("$api/images/random-wikihow") {
        parameter("nsfw", isNsfw)
        header("Authorization", token)
    }

    suspend fun getRandomCutePicture() =
        client.get<RedditPost>("$api/images/random-aww") { header("Authorization", token) }

    suspend fun getRandomNsfw(isGif: Boolean = false) = client.get<RedditPost>("$api/images/random-nsfw") {
        header("Authorization", token)
        parameter("gifs", isGif)
    }

    suspend fun getRandomImageFromSubReddit(subReddit: String, containNsfw: Boolean = false, span: String = "day") =
        client.get<RedditPost>("$api/images/rand-reddit/$subReddit") {
            parameter("remove_nsfw", containNsfw)
            parameter("span", span)
            header("Authorization", token)
        }
}
