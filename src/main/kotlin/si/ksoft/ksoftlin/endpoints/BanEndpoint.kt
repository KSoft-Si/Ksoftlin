package si.ksoft.ksoftlin.endpoints

import io.ktor.client.request.*
import si.ksoft.ksoftlin.builder.client
import si.ksoft.ksoftlin.util.api
import si.ksoft.ksoftlin.util.isNotNull
import si.ksoft.ksoftlin.util.isNotZero

/**
 * The class containing HTTP Calls to the KSoft.Si Ban Endpoint.
 *
 * @since 2.0
 * @author chachy
 */

class BanEndpoint(private val token: String) {
    suspend fun banUser(
        id: Long,
        reason: String,
        proof: String,
        userName: String = "",
        discriminator: Int,
        reporter: Long = 0,
        isAppealPossible: Boolean? = null
    ) = client.post<Boolean>("$api/bans/add") {
        header("Authorization", token)
        parameter("user", id)
        parameter("reason", reason)
        parameter("proof", proof)
        reporter.takeIf { it.isNotZero() }?.let { parameter("mod", this) }
        userName.takeIf { it.isNotBlank() }?.let { parameter("user_name", this) }
        discriminator.takeIf { it.isNotZero() }?.let { parameter("user_discriminator", this) }
        isAppealPossible.takeIf { it.isNotNull() }?.let { parameter("appeal_possible", this) }
    }

    suspend fun isBanned(id: Long) = client.get<Boolean>("$api/bans/check") {
        header("Authorization", token)
        parameter("user", id)
    }

    suspend fun removeBan(id: Long, isForceDelete: Boolean = false) = client.delete<Boolean>("$api/bans/delete") {
        header("Authorization", token)
        parameter("user", id)
        parameter("force", isForceDelete)
    }
}