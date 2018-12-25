import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
}

group = "be.inniger"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(group = "io.vavr", name = "vavr-kotlin", version = "0.9.2")
    testImplementation(kotlin("test-junit"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

repositories {
    jcenter()
}

tasks.withType<Wrapper> {
    gradleVersion = "5.0"
    Wrapper.DistributionType.ALL
}
