import org.gradle.kotlin.dsl.DependencyHandlerScope

/**
 * Single line for adding essential dependencies.
 * Kotlin stdlib, android core ktx.
 * Android UI: extensions, appcompat, material.
 * Android Architecture components.
 * Hilt.
 */
fun DependencyHandlerScope.addDefaultDependencies() {
    api(Libs.KOTLIN_STDLIB)
    api(Libs.CORE_KTX)

    // UI
    api(Libs.ACTIVITY_KTX)
    api(Libs.FRAGMENT_KTX)
    api(Libs.APPCOMPAT)
    api(Libs.MATERIAL)
    api(Libs.CONSTRAINT_LAYOUT)

    // Architecture Components
    api(Libs.LIFECYCLE_VIEW_MODEL_KTX)
    api(Libs.LIFECYCLE_LIVE_DATA_KTX)
    api(Libs.LIFECYCLE_RUNTIME_KTX)
    kapt(Libs.LIFECYCLE_COMPILER)
    api(Libs.NAVIGATION_FRAGMENT_KTX)
    api(Libs.NAVIGATION_UI_KTX)

    // Dagger Hilt
    api(Libs.HILT_ANDROID)
    api(Libs.HILT_VIEWMODEL)
    kapt(Libs.HILT_COMPILER)
    kapt(Libs.ANDROIDX_HILT_COMPILER)

    // Glide
    api(Libs.GLIDE)
    kapt(Libs.GLIDE_COMPILER)

    api(Libs.GSON)
    api(Libs.RETROFIT)
    api(Libs.RETROFIT_CONVERTER)
    api(Libs.OKHTTP)
    api(Libs.OKHTTP_LOGGING_INTERCEPTOR)
}

private fun DependencyHandlerScope.kapt(lib: String) = add("kapt", lib)
private fun DependencyHandlerScope.api(lib: String) = add("api", lib)

