class Application private constructor(
        val name: String,
        val alias: String,
        val packageId: String,
        val googleMapsKey: String,
        val port: String,
        val host: String,
        val storePassword: String,
        val keyPassword: String,
        val openCV: Boolean,
        val withTablet: Boolean,
        val cantTransferToAnyContragentInBetweenAccountPayment: Boolean,
        val resConfigs: Array<String>,
        val connections: String) {

    constructor(builder: Builder) : this(
            builder.name,
            builder.alias,
            builder.packageId,
            builder.googleMapsKey,
            builder.port,
            builder.host,
            builder.storePassword,
            builder.keyPassword,
            builder.openCV,
            builder.withTablet,
            builder.cantTransferToAnyContragentInBetweenAccountPayment,
            builder.resConfigs,
            builder.connections)

    companion object {
        fun create(init: Builder.() -> Unit) = Builder(init).build()
    }

    class Builder private constructor() {

        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        lateinit var name: String
        lateinit var alias: String
        lateinit var packageId: String
        lateinit var googleMapsKey: String
        lateinit var port: String
        lateinit var host: String
        var connections: String = ""
        lateinit var storePassword: String
        lateinit var keyPassword: String
        var withTablet: Boolean = false
        var cantTransferToAnyContragentInBetweenAccountPayment: Boolean = false
        var openCV: Boolean = false
        lateinit var resConfigs: Array<String>

        fun build() : Application{
            if(!::alias.isInitialized) alias = name
            return Application(this)
        }
    }
}

fun application(init: Application.Builder.() -> Unit) = Application.create(init)

fun printLogo() {
    println("""
        Hello
    """.trimIndent())
}