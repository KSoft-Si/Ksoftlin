package si.ksoft.ksoftlin

import si.ksoft.ksoftlin.endpoints.ImageEndpoint
import si.ksoft.ksoftlin.endpoints.LyricsEndpoint

class KsoftApi(token: String) {
    val imageEndpoint = ImageEndpoint(token)
    val lyricsEndpoint = LyricsEndpoint(token)
}
