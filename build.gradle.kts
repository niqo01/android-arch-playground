import org.jetbrains.kotlin.gradle.plugin.KaptExtension

buildscript {
    extra.apply {
        set("isCiBuild", System.getenv("CI") == "true")
    }
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Config.Plugins.android)
        classpath(kotlin("gradle-plugin", version = Config.kotlinVersion))
        classpath(Config.Plugins.ktlint)
        classpath(Config.Plugins.navigation)
        classpath(Config.Plugins.ossLicenses)
        classpath(Config.Plugins.gradleVersions)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }

    tasks.withType(JavaCompile::class).all {
        options.compilerArgs.add(
            "-Adagger.formatGeneratedSource=disabled"
        )
    }
    afterEvaluate {
        val kapt = extensions.findByName("kapt") as KaptExtension?
        if (kapt != null) {
            kapt.arguments {
                arg("dagger.formatGeneratedSource", "disabled")
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}