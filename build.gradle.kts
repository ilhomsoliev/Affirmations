buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.3.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        // Hilt DI
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.45")
        //Google Service
        classpath ("com.google.gms:google-services:4.3.15")
    }
}
