plugins {
    id("android-feature-module")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

dependencies {
    api(project(":ui"))
    implementation(project(":core"))
    ksp(libs.hilt.android.compiler)
}