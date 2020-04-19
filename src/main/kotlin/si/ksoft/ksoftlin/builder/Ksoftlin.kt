package si.ksoft.ksoftlin.builder

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.Json
import si.ksoft.ksoftlin.endpoints.KsoftEndpoints

/**
 * Initialize the Ktor HTTP Client.
 *
 * @since 2.0
 * @author chachy
 */

val client = HttpClient(Apache) {
    Json {
        serializer = GsonSerializer()
    }
}


class Ksoftlin {
    companion object Builder {
        operator fun invoke(builder: KsoftlinBuilder.() -> Unit) = KsoftEndpoints(
            "Bearer ${KsoftlinBuilder().apply(builder).cfg.token}"
        )
    }
}

