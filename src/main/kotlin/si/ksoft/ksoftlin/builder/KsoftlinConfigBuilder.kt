package si.ksoft.ksoftlin.builder

internal data class KsoftlinConfig(var token: String? = null)

class KsoftlinConfigBuilder {
    lateinit var token: String
    internal fun build(): KsoftlinConfig =
        KsoftlinConfig(token)
}