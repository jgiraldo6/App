val composeVersion = rootProject.extra.get("compose_version") as String

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jamar.appvendedor"
    compileSdk = 33

    defaultConfig {
        applicationId = project.property("applicationid") as String
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = project.property("version") as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {
        getByName("release") {
            isDebuggable = false
        }
        getByName("debug") {
            isDebuggable = true
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("dev") {
            applicationId = (project.property("applicationid") as String) + ".dev"
            buildConfigField("String", "STAGE", "\"dev\"")
        }
        create("qa") {
            applicationId = (project.property("applicationid") as String) + ".qa"
            buildConfigField("String", "STAGE", "\"qa\"")
        }
        create("prd") {
            applicationId = project.property("applicationid") as String
            buildConfigField("String", "STAGE", "\"prd\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //implementation(project(path = ":app", configuration = "dev"))
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material3:material3:1.0.0-alpha02")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}

//Codigo que convierte las properties de los archivos .properties en variables de entorno
/*val stage = project.property("stage")
android.buildTypes.map {
    buildType ->
        buildType.buildConfigField("String", "STAGE", "\"${stage}\"")
}*/

