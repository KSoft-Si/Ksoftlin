package si.ksoft.ksoftlin.endpoints

import io.ktor.client.request.*
import si.ksoft.ksoftlin.builder.client
import si.ksoft.ksoftlin.constants.api
import si.ksoft.ksoftlin.data.dbl.vote.VoteResponse
import si.ksoft.ksoftlin.data.dbl.vote.list.ListVoteResponse
import si.ksoft.ksoftlin.data.image.Image
import si.ksoft.ksoftlin.data.image.reddit.RedditPost
import si.ksoft.ksoftlin.data.image.tags.tag.TagResponse
import si.ksoft.ksoftlin.data.image.tags.tag.search.SearchTagResponse
import si.ksoft.ksoftlin.data.image.wikihow.WikiHow
import si.ksoft.ksoftlin.data.kumo.currency.Currency
import si.ksoft.ksoftlin.data.kumo.location.LocationResponse
import si.ksoft.ksoftlin.data.kumo.weather.easy.WeatherResponse
import si.ksoft.ksoftlin.data.music.artist.Artist
import si.ksoft.ksoftlin.data.music.track.Track
import si.ksoft.ksoftlin.data.music.track.lyrics.SongResponse
import si.ksoft.ksoftlin.dsl.utils.isNotNull
import si.ksoft.ksoftlin.dsl.utils.isNotZero
import si.ksoft.ksoftlin.endpoints.utils.enums.ReportType

class KsoftEndpoints(token: String) {
    internal val lyrics = LyricsEndpoint(token)
    internal val image = ImageEndpoint(token)
    internal val ban = BanEndpoint(token)
    internal val dbl = DiscordBotListEndpoint(token)
    internal val kumo = KumoEndpoint(token)
}

internal class LyricsEndpoint(private val token: String) {
    suspend fun getLyrics(song: String, textOnly: Boolean = false) = client.get<SongResponse>("$api/lyrics/search") {
        header("Authorization", token)
        parameter("q", song)
        parameter("text_only", textOnly)
    }

    suspend fun getArtistById(id: Int) = client.get<Artist>("$api/lyrics/artist/$id/") {
        header("Authorization", token)
    }

    suspend fun getTrackById(id: Int) = client.get<Track>("$api/lyrics/track/$id/") {
        header("Authorization", token)
    }
}

internal class ImageEndpoint(private val token: String) {
    suspend fun getRandomImage(tag: String, isNsfw: Boolean = false) = client.get<Image>("$api/images/random-image") {
        parameter("tag", tag)
        parameter("nsfw", isNsfw)
        header("Authorization", token)
    }

    suspend fun getTags() = client.get<TagResponse>("$api/images/tags") { header("Authorization", token) }

    suspend fun searchTag(tag: String) =
        client.get<SearchTagResponse>("$api/images/tags/$tag") { header("Authorization", token) }

    suspend fun getImageFromSnowflake(snowflake: String) =
        client.get<Image>("$api/images/image/$snowflake") { header("Authorization", token) }

    suspend fun getRandomMeme() = client.get<RedditPost>("$api/images/random-meme") { header("Authorization", token) }

    suspend fun getRandomWikiHow(isNsfw: Boolean = false) = client.get<WikiHow>("$api/images/random-wikihow") {
        parameter("nsfw", isNsfw)
        header("Authorization", token)
    }

    suspend fun getRandomCutePicture() =
        client.get<RedditPost>("$api/images/random-aww") { header("Authorization", token) }

    suspend fun getRandomNsfw(isGif: Boolean = false) = client.get<RedditPost>("$api/images/random-nsfw") {
        header("Authorization", token)
        parameter("gifs", isGif)
    }

    suspend fun getRandomImageFromSubReddit(subreddit: String, containNsfw: Boolean = false, span: String = "day") =
        client.get<RedditPost>("$api/images/rand-reddit/$subreddit") {
            parameter("remove_nsfw", containNsfw)
            parameter("span", span)
            header("Authorization", token)
        }
}

internal class BanEndpoint(private val token: String) {
    suspend fun banUser(
        id: Long,
        reason: String,
        proof: String,
        userName: String = "",
        discriminator: Int,
        reporter: Long = 0,
        isAppealPossible: Boolean? = null
    ) = client.post<Boolean>("$api/bans/add") {
        header("Authorization", token)
        parameter("user", id)
        parameter("reason", reason)
        parameter("proof", proof)
        reporter.takeIf { it.isNotZero() }.run { parameter("mod", this) }
        userName.takeIf { it.isNotBlank() }.run { parameter("user_name", this) }
        discriminator.takeIf { it.isNotZero() }.run { parameter("user_discriminator", this) }
        isAppealPossible.takeIf { it.isNotNull() }.run { parameter("appeal_possible", this) }
    }

    suspend fun isBanned(id: Long) = client.get<Boolean>("$api/bans/check") {
        header("Authorization", token)
        parameter("user", id)
    }

    suspend fun removeBan(id: Long, isForceDelete: Boolean = false) = client.delete<Boolean>("$api/bans/delete") {
        header("Authorization", token)
        parameter("user", id)
        parameter("force", isForceDelete)
    }
}

internal class DiscordBotListEndpoint(private val token: String) {
    suspend fun isBotVotedByUser(botId: Long, userId: Long) = client.get<VoteResponse>("$api/webhook/dbl/check") {
        header("Authorization", token)
        parameter("bot", botId)
        parameter("user", userId)
    }

    suspend fun getListOfVotes(botId: Long) = client.get<ListVoteResponse>("$api/webhook/dbl/list") {
        header("Authorization", token)
        parameter("bot", botId)
    }
}

internal class KumoEndpoint(private val token: String) {
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

    suspend fun getWeather(
        reportType: ReportType,
        location: String,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherResponse>("$api/kumo/weather/${reportType.reportType}") {
        header("Authorization", token)
        parameter("q", location)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }

    suspend fun getWeatherWithLongitudeAndLatitude(
        reportType: ReportType,
        longitude: Double,
        latitude: Double,
        units: String = "auto",
        lang: String = "en",
        icons: String = "original"
    ) = client.get<WeatherResponse>("$api/kumo/weather/$latitude,$longitude/${reportType.reportType}") {
        header("Authorization", token)
        parameter("units", units)
        parameter("lang", lang.toLowerCase())
        parameter("icons", icons)
    }


    suspend fun getLocationDataFromIP(ip: String) = client.get<LocationResponse>("$api/kumo/geoip") {
        header("Authorization", token)
        parameter("ip", ip)
    }

    suspend fun convertCurrency(fromCurrency: String, toCurrency: String, value: String) =
        client.get<Currency>("$api/kumo/currency") {
            header("Authorization", token)
            parameter("from", fromCurrency)
            parameter("to", toCurrency)
            parameter("value", value)
        }
}
