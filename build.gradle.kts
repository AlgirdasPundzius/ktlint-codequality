import studio.forface.easygradle.dsl.`kotlin-test-junit`
import studio.forface.easygradle.dsl.`kotlin-test`
import studio.forface.easygradle.dsl.kotlin
import studio.forface.easygradle.dsl.publish

buildscript {
    val kotlinVersion = "1.3.50"

    repositories {
        mavenCentral()
        jcenter()
    }
    dependencies {
        // noinspection all
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("studio.forface.easygradle:dsl:0.16")
    }
}

plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.3.50"
}

group = "pm.algirdas"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    val ktlintVersion = "0.34.2"

    compileOnly(kotlin)
    compileOnly("com.pinterest.ktlint:ktlint-core:$ktlintVersion")
    testCompile("com.pinterest.ktlint:ktlint-core:$ktlintVersion")
    testCompile(`kotlin-test`)
    testCompile(`kotlin-test-junit`)
}

publish {
    projectName = null // We don't have any sub-project child of the root project

    username = "My Bintray username"
    apiKey = "My Bintray Api key"

    groupName = "KtLint Gitlab CodeQuality"
    bintrayGroup = "Bintray group without artifact name" // ES: 'ch.protonmail'
    groupId = "Id of the group" // Last segment of the ground
    artifact = "Name of the artifact" // Artifact

    // Example: 'bintray.group.groupId:artifact'
    // Publish with `./gradlew bintrayUpload`
}