package si.ksoft.ksoftlin.endpoints

class KsoftEndpoints(token: String) {
    val lyrics = LyricsEndpoint(token)
    val image = ImageEndpoint(token)
    val ban = BanEndpoint(token)
    val dbl = DiscordBotListEndpoint(token)
    val kumo = KumoEndpoint(token)
}