package si.ksoft.ksoftlin.endpoints

import io.ktor.client.request.*
import si.ksoft.ksoftlin.builder.client
import si.ksoft.ksoftlin.data.music.artist.Artist
import si.ksoft.ksoftlin.data.music.track.Track
import si.ksoft.ksoftlin.data.music.track.lyrics.SongResponse
import si.ksoft.ksoftlin.util.api

/**
 * The class containing HTTP Calls to the KSoft.Si Lyrics Endpoint.
 *
 * @since 2.0
 * @author chachy
 */

class LyricsEndpoint(private val token: String) {
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
