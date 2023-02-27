package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaCost
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
class GachaCostMapperTest(@Autowired private val gachaCostMapper: GachaCostMapper) {
    @Test
    fun findAllByGachaId_no_data() {
        val gachaCostList: List<GachaCost> = gachaCostMapper.findAllByGachaId(1L, "gacha_id")
        Assertions.assertSame(0, gachaCostList.size)
    }

    @Sql("/sql/GachaCostMapperTest/findAllByGachaId_exist_data.sql")
    @Test
    fun findAllByGachaId_exist_data() {
        val gachaCostList: List<GachaCost> = gachaCostMapper.findAllByGachaId(1L, "gacha_id")
        Assertions.assertSame(1, gachaCostList.size)
        Assertions.assertSame(1L, gachaCostList[0].id)
        Assertions.assertSame(1L, gachaCostList[0].gachaId)
        Assertions.assertSame(0, gachaCostList[0].costType)
        Assertions.assertSame(0, gachaCostList[0].cost)
        Assertions.assertTrue(gachaCostList[0].created == "2022-01-01 00:00:00")
    }

    @Test
    fun findAll_no_data() {
        val gachaCostList: List<GachaCost> = gachaCostMapper.findAll("gacha_id")
        Assertions.assertSame(0, gachaCostList.size)
    }

    @Sql("/sql/GachaCostMapperTest/findAll_exist_data.sql")
    @Test
    fun findAll_exist_data() {
        val gachaCostList: List<GachaCost> = gachaCostMapper.findAll("gacha_id")
        Assertions.assertSame(1, gachaCostList.size)
        Assertions.assertSame(1L, gachaCostList[0].id)
        Assertions.assertSame(1L, gachaCostList[0].gachaId)
        Assertions.assertSame(0, gachaCostList[0].costType)
        Assertions.assertSame(0, gachaCostList[0].cost)
        Assertions.assertTrue(gachaCostList[0].created == "2022-01-01 00:00:00")
    }
}
