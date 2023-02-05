package com.sireikan.gack.module.infrastructure.repository

import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
@Testcontainers
@Transactional
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    companion object {
        @JvmStatic
        @Container
        private val mysql: MySQLContainer<*> = MySQLContainer(DockerImageName.parse("mysql:8.0")).withUsername("user").withPassword("password").withDatabaseName("sample")

        @JvmStatic
        @DynamicPropertySource
        fun mysqlProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysql::getJdbcUrl)
        }
    }

    @Test
    fun isRunning() {
        Assertions.assertTrue(mysql.isRunning)
    }
}
