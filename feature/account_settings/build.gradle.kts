plugins {
    id("android-feature-module")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

dependencies {
    ksp(libs.hilt.android.compiler)
}
