import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jlleitschuh.gradle.ktlint")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.android.gms.oss-licenses-plugin")
    id("com.github.ben-manes.versions")
}

android {

    val isCiBuild = rootProject.extra["isCiBuild"] as Boolean

    compileSdkVersion(Config.SdkVersions.compile)

    buildToolsVersion = Config.buildToolsVersion
    defaultConfig {
        applicationId = "com.nicolasmilliard.playground"

        minSdkVersion(Config.SdkVersions.min)
        targetSdkVersion(Config.SdkVersions.target)

        versionCode = Config.Versions.code
        versionName = Config.Versions.name

        resConfigs("en")

        buildConfigField("boolean", "IS_CI_BUILD", "false")
        buildConfigField("String", "COMMIT_SHA", "\"${gitSha()}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions.annotationProcessorOptions.arguments(
            mapOf(
                "butterknife.debuggable" to "false",
                "butterknife.minSdk" to Config.SdkVersions.min.toString()
            )
        )

        resValue("string", "applicationId", applicationId!!)
    }

    signingConfigs {
        getByName("debug") {
            storeFile = File("app/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
        if (file("app/upload.keystore").exists()) {
            create("upload") {
                storeFile = rootProject.file("upload.keystore")
                storePassword = System.getenv("UPLOAD_STORE_PASSWORD")
                keyAlias = "playground"
                keyPassword = System.getenv("UPLOAD_KEY_PASSWORD")
            }
        }
    }

    buildTypes {

        getByName("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField("boolean", "IS_CI_BUILD", isCiBuild.toString())

            resValue("string", "applicationId", defaultConfig.applicationId + applicationIdSuffix)
        }

        getByName("release") {
            if (file("upload.keystore").exists()) {
                signingConfig = signingConfigs.getByName("upload")
            } else {
                signingConfig = signingConfigs.getByName("debug")
            }
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        textReport = true
        textOutput("stdout")
        setLintConfig(rootProject.file("app/lint.xml"))

        isCheckDependencies = true
        isCheckTestSources = true
        isExplainIssues = false

        // We run a full lint analysis as build part in CI, so skip vital checks for assemble task.
        isCheckReleaseBuilds = false

        testOptions {
            unitTests.isIncludeAndroidResources = true
        }
    }
    
    packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
    }
}

dependencies {

    implementation(project(":item-api"))
    debugImplementation(Config.Libs.LeakCanary.leakCanary)
    releaseImplementation(Config.Libs.LeakCanary.leakCanaryNoop)
    debugImplementation(Config.Libs.LeakCanary.leakCanaryFragments)

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    implementation(Config.Libs.Kotlin.Coroutine.core)
    implementation(Config.Libs.Kotlin.Coroutine.android)

    implementation(Config.Libs.AndroidX.appCompat)
    implementation(Config.Libs.AndroidX.activityKtx)
    implementation(Config.Libs.AndroidX.fragmentKtx)
    implementation(Config.Libs.AndroidX.preferenceKtx)
    implementation(Config.Libs.AndroidX.coreKtx)
    implementation(Config.Libs.AndroidX.constraintLayout)
    implementation(Config.Libs.AndroidX.recyclerView)
    implementation(Config.Libs.AndroidX.viewModelKtx)
    implementation(Config.Libs.AndroidX.pagingRx)
    implementation(Config.Libs.AndroidX.pagingRuntimeKtx)
    implementation(Config.Libs.AndroidX.dynamicAnimation)
    implementation(Config.Libs.AndroidX.viewPager2)
    implementation(Config.Libs.AndroidX.navigationFragmentKtx)
    implementation(Config.Libs.AndroidX.navigationUiKtx)
    implementation(Config.Libs.AndroidX.workRuntimeKtx)
    implementation(Config.Libs.AndroidX.workRxJava)

    implementation(Config.Libs.material)

    implementation(Config.Libs.PlayServices.ossLicenses)

    implementation(Config.Libs.Dagger.core)
    implementation(Config.Libs.Dagger.android)
    kapt(Config.Libs.Dagger.compiler)
    kapt(Config.Libs.Dagger.androidProcessor)
    compileOnly(Config.Libs.Dagger.assistedInject)
    kapt(Config.Libs.Dagger.assistedInjectProcessor)

    if (properties.containsKey("android.injected.invoked.from.ide")) {
        implementation(Config.Libs.ButterKnife.reflect)
    } else {
        implementation(Config.Libs.ButterKnife.core)
        kapt(Config.Libs.ButterKnife.compiler)
    }

    implementation(Config.Libs.timber)

    implementation(Config.Libs.rxJava)
    implementation(Config.Libs.rxRelay)

    implementation(Config.Libs.picasso)
    implementation(Config.Libs.okHttp)
    implementation(Config.Libs.okIo)
    implementation(Config.Libs.shimmer)

    testImplementation(Config.Libs.Test.junit)
    testImplementation(Config.Libs.Test.truth)
    testImplementation(Config.Libs.Test.espressoCore)
    testImplementation(Config.Libs.Test.robolectric)
    testImplementation(Config.Libs.Kotlin.Coroutine.test)
    testImplementation(Config.Libs.AndroidX.pagingCommon)
    testImplementation(Config.Libs.AndroidX.fragmentTesting)
    testImplementation(Config.Libs.AndroidX.workTesting)
}

fun gitSha(): String {
    val f = File(buildDir, "commit-sha.txt")
    if (!f.exists()) {
        val p = Runtime.getRuntime().exec("git rev-parse HEAD", null, project.rootDir)
        val input = p.inputStream.bufferedReader().use { it.readText().trim() }
        if (p.waitFor() != 0) {
            throw RuntimeException(p.errorStream.bufferedReader().use { it.readText() })
        }
        f.parentFile.mkdirs()
        f.writeText(input)
    }
    return f.readText()
}