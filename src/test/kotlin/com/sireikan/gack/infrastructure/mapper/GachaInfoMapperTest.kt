package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaInfo
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
class GachaInfoMapperTest(@Autowired private val gachaInfoMapper: GachaInfoMapper) {
    @Test
    fun find_no_data() {
        val gachaInfo: GachaInfo? = gachaInfoMapper.find(1L)
        Assertions.assertNull(gachaInfo)
    }

    @Sql("/sql/GachaInfoMapperTest/find_exist_data.sql")
    @Test
    fun find_exist_data() {
        val gachaInfo: GachaInfo = gachaInfoMapper.find(1L) ?: return
        Assertions.assertSame(1L, gachaInfo.id)
        Assertions.assertSame(1L, gachaInfo.gachaId)
        Assertions.assertTrue(gachaInfo.gachaName == "name")
        Assertions.assertTrue(gachaInfo.bannerImage == "https://hoge.png")
        Assertions.assertSame(1, gachaInfo.execCount)
        Assertions.assertTrue(gachaInfo.created == "2022-01-01 00:00:00")
    }

    @Test
    fun findAll_no_data() {
        val gachaInfoList: List<GachaInfo> = gachaInfoMapper.findAll("gacha_id")
        Assertions.assertEquals(0, gachaInfoList.size)
    }

    @Sql("/sql/GachaInfoMapperTest/findAll_exist_data.sql")
    @Test
    fun findAll_exist_data() {
        val gachaInfoList: List<GachaInfo> = gachaInfoMapper.findAll("gacha_id")
        Assertions.assertEquals(1, gachaInfoList.size)
        Assertions.assertEquals(1L, gachaInfoList[0].id)
        Assertions.assertEquals(1L, gachaInfoList[0].gachaId)
        Assertions.assertTrue(gachaInfoList[0].gachaName == "name")
        Assertions.assertTrue(gachaInfoList[0].bannerImage == "https://hoge.png")
        Assertions.assertEquals(1, gachaInfoList[0].execCount)
        Assertions.assertTrue(gachaInfoList[0].created == "2022-01-01 00:00:00")
    }

    @Test
    fun insert() {
        val gachaInfo: GachaInfo = GachaInfo.create(1L, 1L, "name", "banner", 1, "2023-01-01 00:00:00")
        gachaInfoMapper.insert(gachaInfo)

        val actual: GachaInfo = gachaInfoMapper.find(1L) ?: Assertions.fail()
        Assertions.assertSame(1L, actual.id)
        Assertions.assertSame(1L, actual.gachaId)
        Assertions.assertTrue(actual.gachaName == "name")
        Assertions.assertTrue(actual.bannerImage == "banner")
        Assertions.assertSame(1, actual.execCount)
        Assertions.assertTrue(actual.created == "2023-01-01 00:00:00")
    }
}
