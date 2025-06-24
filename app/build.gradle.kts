import java.io.FileInputStream
import java.util.Properties

plugins {
    id("android-app-module")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val localProperties = Properties()
localProperties.load(FileInputStream(rootProject.file("local.properties")))

android {
    defaultConfig {
        applicationId = Const.NAMESPACE
        versionCode = 1
        versionName = "1.0"
        targetSdk = Const.COMPILE_SKD

        buildConfigField("String", "apiKey", "\"${localProperties["apiKey"]}\"")
    }
}

dependencies {
    api(project(":core:ui"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:account_info"))
    implementation(project(":core:utils"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    ksp(libs.hilt.android.compiler)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)

    implementation(libs.lottie)
}