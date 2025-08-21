plugins {
kotlin("jvm") version "1.9.24"
id("org.jetbrains.kotlin.plugin.serialization") version "1.9.24"
application
}


repositories {
mavenCentral()
}


val ktor_version = "2.3.11"
val logback_version = "1.4.14"


dependencies {
implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
implementation("io.ktor:ktor-server-html-builder-jvm:$ktor_version")
implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")


implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")
implementation("io.ktor:ktor-client-content-negotiation-jvm:$ktor_version")
implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")


implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")


implementation("ch.qos.logback:logback-classic:$logback_version")


testImplementation(kotlin("test"))
}


java {
toolchain {
languageVersion.set(JavaLanguageVersion.of(17))
}
}


application {
mainClass.set("com.example.ApplicationKt")
}
