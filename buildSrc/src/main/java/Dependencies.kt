import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.example.vocabularyapp"
    const val compile_sdk = 31
    const val min_sdk = 21
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
}

object Versions {
    //Kotlin
    const val stdLib = "1.3.31"
    const val kotlin_core = "1.6.0"

    //RxJava
    const val rxandroid = "2.1.0"
    const val rxJava = " 2.2.8"

    //Retrofit
    const val retrofit = "2.6.0"
    const val converter_gson = "2.6.0"
    const val logging_interceptor = "3.12.1"
    const val rxJava_adapter = "1.0.0"
    const val coroutines_adapter = "0.9.2"
    const val legacy_support = "1.0.0"

    //Room
    const val runtime = "2.2.6"
    const val compiler = "2.2.6"

    //Dagger 2
    const val dagger2_version = "2.39"

    //Cicerone
    const val cicerone = "7.0"

    //Koin
    const val koin_version = "3.1.2"

    //viewBindingDelegate
    const val viewBindingDelegate_version = "1.5.0-beta01"

    //Coroutines
    const val coroutines_core = "1.5.1"
    const val coroutines_android = "1.5.0"

    //Glide
    const val glide_version = "4.11.0"

    //Test
    const val jUnit = "4.12"
    const val test_ext_jUnit = "1.1.3"
    const val espresso_core = "3.4.0"

    //View
    const val appCompat = "1.3.0"
    const val material = "1.4.0"
    const val constraint_layout = "2.0.4"

}

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.stdLib}"
        const val core = "androidx.core:core-ktx:${Versions.kotlin_core}"
    }

    object RxJava {
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
        const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converter_gson}"
        const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
        const val rxJava_adapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxJava_adapter}"
        const val coroutines_adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutines_adapter}"
        const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"
        const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
        const val room_compiler = "androidx.room:room-compiler:${Versions.compiler}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger2_version}"
        const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger2_version}"
        const val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger2_version}"
        const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger2_version}"
    }

    object Cicerone {
        const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
    }

    object Koin {
        const val koin_core = "io.insert-koin:koin-core:${Versions.koin_version}"
        const val koin_android = "io.insert-koin:koin-android:${Versions.koin_version}"
        const val koin_android_compat = "io.insert-koin:koin-android-compat:${Versions.koin_version}"
    }

    object ViewBindingDelegate {
        const val vbDelegate = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBindingDelegate_version}"
        const val noReflection = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBindingDelegate_version}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
    }

    object View {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    }

    object TestImpl {
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val ext_jUnit = "androidx.test.ext:junit:${Versions.test_ext_jUnit}"
        const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    }

