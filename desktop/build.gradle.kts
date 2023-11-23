import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.ExperimentalComposeLibrary


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(project(":common"))
            implementation(project(":common-ui"))
            implementation(project(":communication:drivers:mqtt"))
            implementation(project(":communication:connectors:common"))
            implementation(project(":communication:connectors:home"))


            implementation(Deps.Koin.core)
            implementation("ch.qos.logback:logback-core:1.4.0")
            implementation("ch.qos.logback:logback-classic:1.4.0")
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "IoT Explorer Desktop"
            packageVersion = "1.0.0"
        }
    }
}