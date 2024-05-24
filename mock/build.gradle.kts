plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.javax)
    implementation(libs.retrofit2)
    implementation(libs.moshi.converter)

}