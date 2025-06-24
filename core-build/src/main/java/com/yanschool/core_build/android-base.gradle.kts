package com.yanschool.core_build

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions

plugins {
    id("org.jetbrains.kotlin.android")
}

kotlin {
    KotlinAndroidProjectExtension.compilerOptions {
        KotlinJvmCompilerOptions.jvmTarget = JvmTarget.JVM_11
    }
}
