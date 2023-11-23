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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("org.jetbrains.kotlinx:atomicfu:0.18.5")
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