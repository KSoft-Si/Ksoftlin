package si.ksoft.ksoftlin.data.music.track

import si.ksoft.ksoftlin.data.music.artist.Album

data class Artist(val id: Int, val name: String)

data class Track(val name: String, val artist: Artist, val albums: MutableList<Album>, val lyrics: String)