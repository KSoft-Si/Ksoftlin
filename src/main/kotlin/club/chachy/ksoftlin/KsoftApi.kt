package club.chachy.ksoftlin

import club.chachy.ksoftlin.endpoints.ImageEndpoint
import club.chachy.ksoftlin.endpoints.LyricsEndpoint

class KsoftApi(token: String) {
    val imageEndpoint = ImageEndpoint(token)
    val lyricsEndpoint = LyricsEndpoint(token)
}
