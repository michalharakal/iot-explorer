plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm {
        compilations.all { kotlinOptions.jvmTarget = "11" }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        named("commonMain") {
            dependencies {
                api(project(":communication:drivers:common"))
                api(project(":communication:connectors:common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("org.jetbrains.kotlinx:atomicfu:0.18.5")
            }
        }

        named("jvmMain") {
            dependencies {
                implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")
                implementation("org.slf4j:slf4j-api:2.0.0")
            }
        }
    }
}