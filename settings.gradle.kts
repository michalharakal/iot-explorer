pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

//include(":android")
include(":desktop")
include(":common")
include(":common-ui")
include("communication:connectors:common")
include("communication:connectors:home")
include("communication:connectors:plc:plc4j")
include("communication:drivers:common")
include("communication:drivers:mqtt")
include("communication:drivers:knxnetip")
