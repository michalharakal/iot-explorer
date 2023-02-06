@file:Suppress("UNUSED_VARIABLE")

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("app.cash.molecule")
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
                api(project(":communication:connectors:common"))

                implementation(Deps.Kotlinx.coroutinesCore)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("org.jetbrains.kotlinx:atomicfu:0.18.5")
                implementation("app.cash.molecule:molecule-runtime:0.7.0")
            }
        }

        named("jvmMain") {
            dependencies {
                implementation("org.apache.plc4x:plc4j-api:0.9.1")
                implementation("org.apache.plc4x:plc4j-driver-knxnetip:0.9.1")
                implementation("org.apache.plc4x:plc4j-driver-modbus:0.9.1")


                implementation("org.slf4j:slf4j-api:2.0.0")
            }
        }

        val commonTest by getting {
            dependencies {
                // implementation(kotlin("test"))
            }
        }
    }
}