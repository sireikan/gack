package info.sireikan.sample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import java.security.Security
import java.util.Properties


@EnableAsync
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    Security.setProperty("networkaddress.cache.ttl", "60")
    Security.setProperty("networkaddress.cache.negative.ttl", "10")

    val springApplication = SpringApplication(Application::class.java)
    val properties = Properties()
    properties.setProperty("spring.profiles.active", "localhost")
    properties.setProperty("spring.datasource.initialization-mode", "NEVER")
    properties.setProperty("spring.resources.add-mappings", "true")
    springApplication.setDefaultProperties(properties)
    runApplication<Application>(*args)
}
