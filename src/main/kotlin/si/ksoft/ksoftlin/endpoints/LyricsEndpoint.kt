package si.ksoft.ksoftlin.endpoints

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import si.ksoft.ksoftlin.data.*

class LyricsEndpoint(private val token: String) {
    private val url = "https://api.ksoft.si/lyrics"
    private val client = OkHttpClient.Builder().build()

    fun getLyrics(songName: String, textOnly: Boolean = false, limit: Int = 10): MutableList<Song> {
        val songs = mutableListOf<Song>()
        val req = Request.Builder().url("$url/search?q=$songName&text_only=$textOnly&limit=$limit")
            .header("Authorization", "Bearer $token").build()
        var data: JsonObject? = null
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        for (s in data!!["data"].asJsonArray) {
            val song = Song(
                s.asJsonObject["artist"].asString,
                s.asJsonObject["artist_id"].asInt,
                s.asJsonObject["album"].asString,
                s.asJsonObject["album_ids"].asString,
                s.asJsonObject["album_year"].asString,
                s.asJsonObject["name"].asString,
                s.asJsonObject["lyrics"].asString,
                s.asJsonObject["search_str"].asString,
                s.asJsonObject["album_art"].asString,
                s.asJsonObject["popularity"].asInt,
                s.asJsonObject["id"].asString,
                s.asJsonObject["search_score"].asDouble
            )
            songs.add(song)
        }
        return songs
    }

    /*
    TODO: Music Recommendations
     */

    fun getArtistByID(id: Int): Artist {
        val req = Request.Builder().url("$url/artist/$id").header("Authorization", "Bearer $token").build()
        var data: JsonObject? = null
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val albums = mutableListOf<Artist>()
        val tracks = mutableListOf<Track>()
        for (t in data!!["tracks"].asJsonArray) {
            tracks.add(
                Track(
                    t.asJsonObject["id"].asInt,
                    t.asJsonObject["name"].asString
                )
            )
        }
        for (a in data["albums"].asJsonArray) {
            albums.add(
                Artist(
                    a.asJsonObject["id"].asInt,
                    a.asJsonObject["name"].asString,
                    a.asJsonObject["year"].asInt
                )
            )
        }
        return Artist(
            data["id"].asInt,
            data["name"].asString,
            0,
            albums,
            tracks
        )
    }

    fun getAlbumByID(id: Int): Album {
        val req = Request.Builder().url("$url/album/$id").header("Authorization", "Bearer $token").build()
        var data: JsonObject? = null
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val artists = mutableListOf<Artist>()
        val tracks = mutableListOf<Track>()
        for (a in data!!["artist"].asJsonArray) {
            artists.add(
                Artist(
                    a.asJsonObject["id"].asInt,
                    a.asJsonObject["name"].asString
                )
            )
        }
        for (t in data["artist"].asJsonArray) {
            tracks.add(
                Track(
                    t.asJsonObject["id"].asInt,
                    t.asJsonObject["name"].asString
                )
            )
        }
        return Album(
            data["id"].asInt,
            data["name"].asString,
            data["year"].asInt,
            artists,
            tracks
        )
    }

    fun getTrackByID(id: Int): RetrievedTrack {
        val req = Request.Builder().url("$url/lyrics/track/$id").header("Authorization", "Bearer $token").build()
        var data: JsonObject? = null
        try {
            data = JsonParser.parseString(client.newCall(req).execute().body!!.string()).asJsonObject
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val albums = mutableListOf<Artist>()
        for (a in data!!["albums"].asJsonArray) {
            albums.add(
                Artist(
                    a.asJsonObject["id"].asInt,
                    a.asJsonObject["name"].asString,
                    a.asJsonObject["year"].asInt
                )
            )
        }
        return RetrievedTrack(
            data["name"].asString,
            Artist(
                data["artist"].asJsonObject["id"].asInt,
                data["artist"].asJsonObject["name"].asString
            ),
            albums,
            data["lyrics"].asString
        )
    }
}

