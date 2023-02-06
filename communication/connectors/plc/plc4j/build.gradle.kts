plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":communication:drivers:common"))

    implementation("org.apache.plc4x:plc4j-api:0.9.1")
    implementation("org.apache.plc4x:plc4j-driver-knxnetip:0.9.1")
    implementation("org.apache.plc4x:plc4j-driver-modbus:0.9.1")


    implementation("org.slf4j:slf4j-api:2.0.0")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}