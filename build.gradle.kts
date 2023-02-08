import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
    id("org.openapi.generator") version "6.3.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "com.sireikan"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.session:spring-session-data-redis")

    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.1")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    runtimeOnly("mysql:mysql-connector-java")

    compileOnly("io.swagger.core.v3:swagger-annotations:2.2.8")
    compileOnly("io.swagger.core.v3:swagger-models:2.2.8")
    compileOnly("jakarta.annotation:jakarta.annotation-api:2.1.1")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit", module = "junit")
    }
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.1")
    testImplementation("org.testcontainers:mysql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-inline")
    testImplementation("org.mockito:mockito-core")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")

    testCompileOnly("junit:junit")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:1.17.2")
    }
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

task<GenerateTask>("generateApiDoc") {
    generatorName.set("html2")
    inputSpec.set("$projectDir/openapi.yaml")
    outputDir.set("$buildDir/openapi/doc/")
}

task<GenerateTask>("generateApiServer") {
    generatorName.set("kotlin-spring")
    inputSpec.set("$projectDir/openapi.yaml")
    outputDir.set("$buildDir/openapi/server-code/") // .gitignoreされているので注意(わざとここにあります)
    apiPackage.set("com.sireikan.gack.openapi.generated.controller") // 各自のアプリケーションに合わせてパス名を変更する
    modelPackage.set("com.sireikan.gack.openapi.generated.model") // 各自のアプリケーションに合わせてパス名を変更する
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
        )
    )
    /**
     * true にすると tags 準拠で、API の interface を生成する
     */
    additionalProperties.set(
        mapOf(
            "useTags" to "true"
        )
    )
}

tasks.compileKotlin {
//    dependsOn("generateApiServer")
}

kotlin.sourceSets.main {
    kotlin.srcDir("$buildDir/openapi/server-code/src/main")
}
