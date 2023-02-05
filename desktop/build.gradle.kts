import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version Versions.composeDesktopWeb
}

repositories {
    mavenCentral()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm { compilations.all { kotlinOptions.jvmTarget = "11" } }
    sourceSets {
        val jvmMain by getting {
            dependencies {
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
        val jvmTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
                implementation(fileTree("../libs") { include("*poet*.jar") })
                implementation(fileTree("../libs") { include("*renderer*.jar") })
                val okioVersion = "3.0.0-alpha.8"
                implementation("com.squareup.okio:okio:$okioVersion")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Impulse Desktop"
            packageVersion = "1.0.0"

            modules("jdk.crypto.ec")

            val iconsRoot = project.file("$rootDir/common/src/desktopMain/resources/images")
            macOS {
                iconFile.set(iconsRoot.resolve("icon-mac.icns"))
            }
            windows {
                iconFile.set(iconsRoot.resolve("icon-windows.ico"))
                menuGroup = "IoT Explorer"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "18159995-d967-4CD2-8885-77BFA97CFA9F"
            }
            linux {
                iconFile.set(iconsRoot.resolve("icon-linux.png"))
            }
        }
    }
}