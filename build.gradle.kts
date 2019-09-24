import org.jetbrains.kotlin.konan.properties.Properties
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
version = "0.1.9"

repositories {
    mavenCentral()
}

dependencies {
    val ktlintVersion = "0.34.2"

    compileOnly(kotlin)
    compile("com.google.code.gson:gson:2.8.5")
    compileOnly("com.pinterest.ktlint:ktlint-core:$ktlintVersion")
    testCompile("com.pinterest.ktlint:ktlint-core:$ktlintVersion")
    testCompile(`kotlin-test`)
    testCompile(`kotlin-test-junit`)
}



publish {

    val local = Properties()
    val localProperties: File = rootProject.file("local.properties")
    if (localProperties.exists()) {
        localProperties.inputStream().use { local.load(it) }
    }

    projectName = null

    username = local.getProperty("username")
    apiKey = local.getProperty("apiKey")

    bintrayGroup = "pm.algirdas"
    groupId = "ktlint"
    groupName = "Ktlint"
    artifact = "reporter"
    gitUrl = "https://github.com/AlgirdasPundzius/ktlint-codequality"
    licenses {
        license {
            name = "MIT"
            url = "https://opensource.org/licenses/MIT"
        }
    }
}
