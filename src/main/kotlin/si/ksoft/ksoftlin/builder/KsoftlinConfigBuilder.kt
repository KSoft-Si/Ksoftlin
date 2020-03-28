package si.ksoft.ksoftlin.builder

internal data class KsoftlinConfig(val token: String)

class KsoftlinConfigBuilder {
    lateinit var token: String
    internal fun build(): KsoftlinConfig =
        KsoftlinConfig(token)
}