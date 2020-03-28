package si.ksoft.ksoftlin.data.music.artist

data class Album(val id: Int, val name: String, val year: Int)

data class Track(val id: Int, val name: String)

data class Artist(val id: Int, val name: String, val albums: List<Album>, val tracks: List<Track>)