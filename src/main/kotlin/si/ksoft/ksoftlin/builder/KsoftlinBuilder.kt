package si.ksoft.ksoftlin.builder


class KsoftlinBuilder {
    internal var cfg = KsoftlinConfig()
    fun config(config: KsoftlinConfigBuilder.() -> Unit) = apply {
        cfg = KsoftlinConfigBuilder()
            .apply(config).build()
    }
}