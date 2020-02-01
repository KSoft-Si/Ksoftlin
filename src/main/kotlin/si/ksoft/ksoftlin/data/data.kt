package si.ksoft.ksoftlin.data

data class Image(val url: String, val snowflake: String, val isNsfw: Boolean, val tag: String)

data class WikihowImage(val url: String, val title: String, val nsfw: Boolean, val articleUrl: String)

data class Tag(val name: String, val isNsfw: Boolean, val tags: MutableList<String>)

data class Song(
    val artist: String,
    val artistId: Int,
    val album: String,
    val albumIds: String,
    val albumYear: String,
    val name: String,
    val lyrics: String,
    val searchString: String,
    val albumArt: String,
    val popularity: Int,
    val id: String,
    val searchScore: Double
)

data class Track(val id: Int, val name: String)

data class Artist(
    val id: Int,
    val name: String,
    val year: Int? = 0,
    val albums: MutableList<Artist>? = mutableListOf(),
    val tracks: MutableList<Track>? = mutableListOf()
)

data class Album(
    val id: Int,
    val name: String,
    val year: Int,
    val artist: MutableList<Artist>,
    val tracks: MutableList<Track>
)

data class RetrievedTrack(val name: String, val artist: Artist, val albums: MutableList<Artist>, val lyrics: String)

data class RedditPost(
    val title: String,
    val imageUrl: String,
    val source: String,
    val subReddit: String,
    val upvotes: Int,
    val downvotes: Int,
    val comments: Int,
    val createdAt: Long,
    val isNsfw: Boolean,
    val author: String
)