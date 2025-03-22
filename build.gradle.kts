plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.17.2"
}

repositories {
    maven {
        url = uri("https://maven.aliyun.com/repository/public")
    }
    maven("https://www.jetbrains.com/intellij-repository/releases")
}

intellij {
    version.set("2024.3.4")
    type.set("IC")
    plugins.add("com.intellij.java")
}

dependencies {
}

tasks {
    wrapper {
        gradleVersion = "8.10"
    }
    buildSearchableOptions {
        enabled = false
    }
}