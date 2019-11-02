plugins {
    kotlin("jvm") version "1.3.50"
}

group = "be.inniger"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(group = "io.vavr", name = "vavr-kotlin", version = "0.10.2")
    testImplementation(kotlin("test-junit"))
}

tasks.wrapper {
    Wrapper.DistributionType.ALL
}

