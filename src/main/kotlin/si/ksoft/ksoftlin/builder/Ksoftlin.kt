package si.ksoft.ksoftlin.builder

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.Json
import si.ksoft.ksoftlin.endpoints.KsoftEndpoints

val client = HttpClient(ClientConfig.client) {
    Json {
        serializer = GsonSerializer()
    }
}

object ClientConfig {
    var client: HttpClientEngineFactory<*> = Apache
}

fun Ksoft(client: HttpClientEngineFactory<*> = Apache, builder: KsoftlinBuilder.() -> Unit) = KsoftEndpoints(
    "Bearer ${KsoftlinBuilder().apply(builder).cfg.token}"
).apply { ClientConfig.client = client }
