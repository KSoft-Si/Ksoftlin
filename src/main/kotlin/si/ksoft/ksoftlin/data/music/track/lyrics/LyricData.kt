package si.ksoft.ksoftlin.data.music.track.lyrics

import com.google.gson.annotations.SerializedName

data class SongResponse(val data: MutableList<Song>)

data class Song(val artist: String,
                val artistId: Int,
                val album: String,
                @SerializedName("album_ids")
                val albumIds: String,
                @SerializedName("album_year")
                val albumYear: String,
                val name: String,
                val lyrics: String,
                @SerializedName("search_str")
                val searchString: String,
                @SerializedName("album_art")
                val albumArt: String,
                val popularity: Int,
                val id: String,
                @SerializedName("search_score")
                val searchScore: Double)

