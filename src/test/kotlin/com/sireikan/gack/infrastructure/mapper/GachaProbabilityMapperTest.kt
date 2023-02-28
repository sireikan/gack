package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaProbability
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
class GachaProbabilityMapperTest(@Autowired private val gachaProbabilityMapper: GachaProbabilityMapper) {
    @Test
    fun findAllByGachaId_no_data() {
        val gachaProbabilityList: List<GachaProbability> = gachaProbabilityMapper.findAllByGachaId(1L, "gacha_id")
        Assertions.assertSame(0, gachaProbabilityList.size)
    }

    @Sql("/sql/GachaProbabilityMapperTest/findAllByGachaId_exist_data.sql")
    @Test
    fun findAllByGachaId_exist_data() {
        val gachaProbabilityList: List<GachaProbability> = gachaProbabilityMapper.findAllByGachaId(1L, "gacha_id")
        Assertions.assertSame(1, gachaProbabilityList.size)
        Assertions.assertSame(1L, gachaProbabilityList[0].id)
        Assertions.assertSame(1L, gachaProbabilityList[0].gachaId)
        Assertions.assertSame(100, gachaProbabilityList[0].probability)
        Assertions.assertSame(0, gachaProbabilityList[0].objectType)
        Assertions.assertSame(0L, gachaProbabilityList[0].objectId)
        Assertions.assertSame(0, gachaProbabilityList[0].objectCount)
        Assertions.assertTrue(gachaProbabilityList[0].created == "2022-01-01 00:00:00")
    }

    @Test
    fun findAll_no_data() {
        val gachaProbabilityList: List<GachaProbability> = gachaProbabilityMapper.findAll("gacha_id")
        Assertions.assertSame(0, gachaProbabilityList.size)
    }

    @Sql("/sql/GachaProbabilityMapperTest/findAll_exist_data.sql")
    @Test
    fun findAll_exist_data() {
        val gachaProbabilityList: List<GachaProbability> = gachaProbabilityMapper.findAll("gacha_id")
        Assertions.assertSame(1, gachaProbabilityList.size)
        Assertions.assertSame(1L, gachaProbabilityList[0].id)
        Assertions.assertSame(1L, gachaProbabilityList[0].gachaId)
        Assertions.assertSame(100, gachaProbabilityList[0].probability)
        Assertions.assertSame(0, gachaProbabilityList[0].objectType)
        Assertions.assertSame(0L, gachaProbabilityList[0].objectId)
        Assertions.assertSame(0, gachaProbabilityList[0].objectCount)
        Assertions.assertTrue(gachaProbabilityList[0].created == "2022-01-01 00:00:00")
    }
}
