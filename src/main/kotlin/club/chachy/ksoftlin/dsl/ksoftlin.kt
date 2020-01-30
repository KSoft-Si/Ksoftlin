package club.chachy.ksoftlin.dsl

import club.chachy.ksoftlin.KsoftApi
import club.chachy.ksoftlin.exceptions.AuthenticationErrorException

class Settings {
    var token: String? = null
}

fun login(settings: Settings.() -> Unit): KsoftApi {
    val s = Settings()
    s.settings()
    if (s.token?.isEmpty()!!) {
        throw AuthenticationErrorException("No token was provided")
    }
    return KsoftApi(s.token!!)
}
