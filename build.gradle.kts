import jdk.tools.jlink.resources.plugins

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
}

group = "IdiotamSpielen"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}