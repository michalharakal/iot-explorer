rootProject.name = "iot-explorer"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

//include(":android")
include(":desktop")
include(":common-ui")
include(":common")
include("communication:connectors:common")
include("communication:connectors:home")
include("communication:drivers:common")
include("communication:connectors:plc:plc4j")
include("communication:drivers:knxnetip")
include("communication:drivers:mqtt")


/*
include(":common-ui")
*/