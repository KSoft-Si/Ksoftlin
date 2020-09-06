package si.ksoft.ksoftlin.data.music.track.lyrics

import com.google.gson.annotations.SerializedName

data class SongResponse(val data: List<Song>)

data class Song(
    val artist: String,
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
    val searchScore: Double,
    val url: String,
    val singalongStamp: List<SingalongStamp>?,
    val meta: Metadata
)

data class Metadata(
    val spotify: SpotifyMetadata,
    val deezer: DeezerMetadata,
    val artist: List<Artist>,
    val other: OtherMetadata
)

data class SpotifyMetadata(
    val artists: List<String>?,
    val track: String?,
    val album: String?
)

data class DeezerMetadata(
    val artist: List<String>?,
    val track: String?,
    val album: String?
)


data class Artist(
    val name: String?,
    @SerializedName("is_primary")
    val isPrimary: Boolean?
)

data class OtherMetadata(
    val gain: Double,
    val bpm: Double
)

data class SingalongStamp(
    @SerializedName("lrc_timestamp")
    val lyricTimestamp: String,
    val milliseconds: String,
    val duration: String,
    val line: String
)