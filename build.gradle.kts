import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
}

group = "be.inniger"
version = "1.0-SNAPSHOT"

dependencies {
    compile(kotlin("stdlib"))
    testCompile(kotlin("test-junit"))
}

repositories {
    jcenter()
}

tasks.withType<Wrapper> {
    gradleVersion = "5.0"
    Wrapper.DistributionType.ALL
}
