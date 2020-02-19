package si.ksoft.ksoftlin.dsl

import si.ksoft.ksoftlin.KsoftApi
import si.ksoft.ksoftlin.exceptions.AuthenticationErrorException

class Settings {
    lateinit var token: String
}

fun login(settings: Settings.() -> Unit): KsoftApi {
    val s = Settings()
    s.settings()
    if (s.token.isEmpty()) {
        throw AuthenticationErrorException("No token was provided")
    }
    return KsoftApi(s.token)
}
