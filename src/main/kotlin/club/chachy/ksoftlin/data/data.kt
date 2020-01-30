package club.chachy.ksoftlin.data

data class Image(val url: String, val snowflake: String, val nsfw: Boolean, val tag: String)

data class WikihowImage(val url: String, val title: String, val nsfw: Boolean, val articleUrl: String)

data class Tag(val name: String, val nsfw: Boolean, val tags: MutableList<String>)

data class Song(val artist: String, val artistId: Int, val album: String, val albumIds: String, val albumYear: String, val name: String, val lyrics: String, val searchString: String, val albumArt: String, val popularity: Int, val id: String, val searchScore: Double)

data class ArtistAlbum(val id: Int, val name: String, val year: Int)

data class Track(val id: Int, val name: String)

data class Artist(val id: Int, val name: String, val albums: MutableList<ArtistAlbum>, val tracks: MutableList<Track>)

data class AlbumArtist(val id: Int, val name: String)

data class Album(val id: Int, val name: String, val year: Int, val artist: MutableList<AlbumArtist>, val tracks: MutableList<Track>)

data class RetrievedTrack(val name: String, val artist: AlbumArtist, val albums: MutableList<ArtistAlbum>, val lyrics: String)

data class RedditPost(val title: String, val imageUrl: String, val source: String, val subReddit: String, val upvotes: Int, val downvotes: Int, val comments: Int, val createdAt: Long, val nsfw: Boolean, val author: String)