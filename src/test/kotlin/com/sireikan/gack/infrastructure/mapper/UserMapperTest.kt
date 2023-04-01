package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.User
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
@Sql(scripts = ["/sql/Common/delete.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserMapperTest(@Autowired private val userMapper: UserMapper) {
    @Test
    fun find_no_data() {
        val user: User? = userMapper.find(1L)
        Assertions.assertNull(user)
    }

    @Sql("/sql/UserMapperTest/find_exist_data.sql")
    @Test
    fun find_exist_data() {
        val user: User = userMapper.find(1L) ?: return
        Assertions.assertEquals(1, user.userId)
        Assertions.assertEquals("test", user.name)
    }

    @Test
    fun findAll_no_data() {
        val userList: List<User> = userMapper.findAll("id")
        Assertions.assertEquals(0, userList.size)
    }

    @Sql("/sql/UserMapperTest/findAll_exist_data.sql")
    @Test
    fun findAll_exist_data() {
        val userList: List<User> = userMapper.findAll("id")
        Assertions.assertEquals(1, userList.size)
        Assertions.assertEquals(1, userList[0].userId)
        Assertions.assertEquals("test", userList[0].name)
    }

    @Sql("/sql/UserMapperTest/findAll_multi.sql")
    @Test
    fun findAll_multi() {
        val userList: List<User> = userMapper.findAll("id")
        Assertions.assertEquals(2, userList.size)
        Assertions.assertEquals(1, userList[0].userId)
        Assertions.assertEquals("test", userList[0].name)
        Assertions.assertEquals(2, userList[1].userId)
        Assertions.assertEquals("test", userList[1].name)
    }

    @Test
    fun insert() {
        userMapper.insert(User.create(
            null,
            1L,
            "test",
            "2022-01-01 00:00:00"
        ))
        val user: User? = userMapper.find(1L)
        if (user == null) {
            Assertions.fail<String>("error")
            return
        }
        Assertions.assertEquals(1, user.userId)
        Assertions.assertEquals("test", user.name)
    }

    @Sql("/sql/UserMapperTest/delete.sql")
    @Test
    fun delete() {
        userMapper.delete(1L)
        val user: User? = userMapper.find(1L)
        Assertions.assertNull(user)
    }
}
