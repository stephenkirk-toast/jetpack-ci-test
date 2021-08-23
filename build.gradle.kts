import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.compose") version "1.0.0-alpha1-rc2"
    id("org.jlleitschuh.gradle.ktlint") version "10.1.0"
}

group = "com.toasttab.pulseman.test"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.google.com") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

val protoktVersion: String by rootProject
val reflectionsVersion: String by rootProject
val kotlinVersion: String by rootProject
val rsyntaxVersion: String by rootProject
val composeVersion: String by rootProject
val jacksonVersion: String by rootProject
val protobufUtils: String by rootProject
val pulsarVersion: String by rootProject
val jUnitVersion: String by rootProject
val assertJVersion: String by rootProject
val testContainerPulsar: String by rootProject
val mockkVersion: String by rootProject
val googleCommonProtos: String by rootProject

dependencies {
    implementation(compose.desktop.currentOs)

    implementation("org.jetbrains.compose.material:material-icons-extended:$composeVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.apache.pulsar:pulsar-client-admin-original:$pulsarVersion")
    testImplementation("org.assertj:assertj-core:$assertJVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$jUnitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$jUnitVersion")
    testImplementation("org.testcontainers:pulsar:$testContainerPulsar")
}

tasks {
    test {
        exclude("**/*IT*.class")
        useJUnitPlatform()
    }
}

task<Test>("iTest") {
    group = "verification"
    useJUnitPlatform()
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_15.toString()
        allWarningsAsErrors = true
    }
}

compose.desktop {
    application {
        mainClass = "com.toasttab.pulseman.test.MainKt"
        nativeDistributions {
            packageName = "Pulseman-test"
            packageVersion = "1.0.1"
            description = "testci"
            copyright = ""

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            includeAllModules = true

            macOS {
                iconFile.set(project.file("pulse.icns"))
                bundleID = "com.toasttab.pulseman.test"
                signing {
                    sign.set(
                        System.getenv("ORG_GRADLE_PROJECT_compose_desktop_mac_sign")?.toString()?.toBoolean() ?: false
                    )
                    identity.set(System.getenv("ORG_GRADLE_PROJECT_compose_desktop_mac_signing_identity") ?: "")
                }
                notarization {
                    appleID.set(System.getenv("ORG_GRADLE_PROJECT_compose_desktop_mac_notarization_appleID") ?: "")
                    password.set(System.getenv("ORG_GRADLE_PROJECT_compose_desktop_mac_notarization_password") ?: "")
                    ascProvider.set(System.getenv("ORG_GRADLE_PROJECT_compose_desktop_mac_notarization_ascProvider") ?: "")
                }
            }
        }
    }
}
