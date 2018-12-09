import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
}

group = "be.inniger"
version = "1.0-SNAPSHOT"

dependencies {
    compile(kotlin("stdlib"))
    compile(group = "org.pcollections", name = "pcollections", version = "3.0.3")
    testCompile(kotlin("test-junit"))
}

repositories {
    jcenter()
}

tasks.withType<Wrapper> {
    gradleVersion = "5.0"
    Wrapper.DistributionType.ALL
}
