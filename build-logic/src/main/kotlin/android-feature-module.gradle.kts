import com.android.build.gradle.LibraryExtension
import gradle.kotlin.dsl.accessors._32a02eef0099c46a598ccc7a8c19c034.api
import gradle.kotlin.dsl.accessors._f265cf380225e5cd02f7700a5b4c9288.implementation

plugins {
    id("android-base")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
}

configure<LibraryExtension> {
    baseAndroidConfig(project)
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:utils"))
    api(project(":core:ui"))

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material)
}
