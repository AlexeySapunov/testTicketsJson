plugins {
    java
}

group = "com.Alexey"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("io.netty:netty-all:4.1.72.Final")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}