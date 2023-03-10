package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaProbabilityLog
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
class GachaProbabilityLogMapperTest(@Autowired private val gachaProbabilityLogMapper: GachaProbabilityLogMapper) {
    @Test
    fun insert() {
        val gachaProbabilityLog: GachaProbabilityLog = GachaProbabilityLog.create(-1L, 1L, 100, 1, 1L, 1, "2022-01-01 00:00:00", null)
        gachaProbabilityLogMapper.insert(gachaProbabilityLog)
    }
}
