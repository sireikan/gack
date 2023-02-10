package com.sireikan.gack.module.infrastructure.repository

import com.sireikan.gack.module.infrastructure.entity.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.junit.jupiter.Testcontainers

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ExtendWith(MysqlExtension::class)
@DirtiesContext
@MybatisTest
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun findAll_no_data() {
        val userList: List<User> = userRepository.findAll()
        Assertions.assertEquals(0, userList.size)
    }

    @Sql("/sql/UserRepositoryTest/findAll_exist_data.sql")
    @Test
    fun findAll_exist_data() {
        val userList: List<User> = userRepository.findAll()
        Assertions.assertEquals(1, userList.size)
        Assertions.assertEquals(1, userList[0].id)
        Assertions.assertEquals("test", userList[0].name)
        Assertions.assertEquals("test@example.com", userList[0].email)
        Assertions.assertEquals("test", userList[0].password)
    }

    @Sql("/sql/UserRepositoryTest/findAll_multi.sql")
    @Test
    fun findAll_multi() {
        val userList: List<User> = userRepository.findAll()
        Assertions.assertEquals(2, userList.size)
        Assertions.assertEquals(1, userList[0].id)
        Assertions.assertEquals("test", userList[0].name)
        Assertions.assertEquals("test1@example.com", userList[0].email)
        Assertions.assertEquals("test", userList[0].password)
        Assertions.assertEquals(2, userList[1].id)
        Assertions.assertEquals("test", userList[1].name)
        Assertions.assertEquals("test2@example.com", userList[1].email)
        Assertions.assertEquals("test", userList[1].password)
    }
}
