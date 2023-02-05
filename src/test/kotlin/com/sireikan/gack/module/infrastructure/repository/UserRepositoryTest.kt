package com.sireikan.gack.module.infrastructure.repository

import com.sireikan.gack.module.infrastructure.entity.User
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

    @Test
    fun getAllTodoList() {
        val userList: List<User> = userRepository.findAll()
        Assertions.assertEquals(2, userList.size)
        Assertions.assertEquals(1, userList[0].id)
        Assertions.assertEquals("服をクリーニングに出す", userList[0].name)
        Assertions.assertEquals(2, userList[1].id)
        Assertions.assertEquals("きのこのマリネを作る", userList[1].name)
    }
}
