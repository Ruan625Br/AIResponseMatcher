plugins {
    kotlin("jvm") version "1.9.22"

}
group = "com.jn.airesponsematcher"
version = "1.0.0-beta"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}