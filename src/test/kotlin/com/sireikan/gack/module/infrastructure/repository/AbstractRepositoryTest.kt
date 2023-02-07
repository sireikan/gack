package com.sireikan.gack.module.infrastructure.repository

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
abstract class AbstractRepositoryTest {
    companion object {
        @JvmStatic
        @Container
        protected val mysql: MySQLContainer<*> = MySQLContainer(DockerImageName.parse("mysql:8.0")).withUsername("user").withPassword("password").withDatabaseName("sample")

        @JvmStatic
        @DynamicPropertySource
        fun mysqlProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysql::getJdbcUrl)
        }
    }
}
