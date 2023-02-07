package com.sireikan.gack.module.infrastructure.repository

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
class MysqlTest : AbstractRepositoryTest() {
    @Test
    fun isRunning() {
        Assertions.assertTrue(mysql.isRunning)
    }
}
