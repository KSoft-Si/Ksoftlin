package si.ksoft.ksoftlin.builder

import io.ktor.client.engine.apache.Apache


class KsoftlinBuilder {
    internal var cfg = KsoftlinConfig("")
    fun config(config: KsoftlinConfigBuilder.() -> Unit) { cfg = KsoftlinConfigBuilder()
        .apply(config).build() }
}