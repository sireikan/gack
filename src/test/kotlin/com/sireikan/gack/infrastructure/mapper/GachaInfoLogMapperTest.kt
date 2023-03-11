package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaInfoLog
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
class GachaInfoLogMapperTest(@Autowired private val gachaInfoLogMapper: GachaInfoLogMapper) {
    @Test
    fun insert() {
        val gachaInfoLog: GachaInfoLog = GachaInfoLog.create(1L, "gacha", "banner", 1, "2022-01-01 00:00:00", null)
        gachaInfoLogMapper.insert(gachaInfoLog)
    }
}
