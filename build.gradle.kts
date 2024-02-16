plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`

}
group = "com.jn.airesponsematcher"
version = "1.0.0-beta"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}
publishing {
    publications {
        create<MavenPublication>("maven"){
            groupId = "com.github.Ruan625Br"
            artifactId = "AIResponseMatcher"
            version = "1.0.0-beta"

            from(components["java"])
        }
    }
}