package com.sireikan.gack.infrastructure.repository

import com.sireikan.gack.domain.model.gacha.CostType
import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.model.gacha.ObjectType
import com.sireikan.gack.domain.repository.GachaOrderKey
import com.sireikan.gack.domain.repository.GachaRepository
import com.sireikan.gack.infrastructure.entity.GachaCost
import com.sireikan.gack.infrastructure.entity.GachaInfo
import com.sireikan.gack.infrastructure.entity.GachaProbability
import com.sireikan.gack.infrastructure.mapper.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GachaDBRepositoryTest {
    private lateinit var gachaInfoMapper: GachaInfoMapper
    private lateinit var gachaInfoLogMapper: GachaInfoLogMapper
    private lateinit var gachaCostMapper: GachaCostMapper
    private lateinit var gachaCostLogMapper: GachaCostLogMapper
    private lateinit var gachaProbabilityMapper: GachaProbabilityMapper
    private lateinit var gachaProbabilityLogMapper: GachaProbabilityLogMapper
    private lateinit var gachaRepository: GachaRepository

    @BeforeEach
    fun setup() {
        gachaInfoMapper = Mockito.mock(GachaInfoMapper::class.java)
        gachaInfoLogMapper = Mockito.mock(GachaInfoLogMapper::class.java)
        gachaCostMapper = Mockito.mock(GachaCostMapper::class.java)
        gachaCostLogMapper = Mockito.mock(GachaCostLogMapper::class.java)
        gachaProbabilityMapper = Mockito.mock(GachaProbabilityMapper::class.java)
        gachaProbabilityLogMapper = Mockito.mock(GachaProbabilityLogMapper::class.java)
        gachaRepository = GachaDBRepository(gachaInfoMapper, gachaInfoLogMapper, gachaCostMapper, gachaCostLogMapper, gachaProbabilityMapper, gachaProbabilityLogMapper)
    }

    @Test
    fun find() {
        Mockito.`when`(gachaInfoMapper.find(1L)).thenReturn(GachaInfo.create(1L, 1L, "name", "banner", 1, "2022-01-01 00:00:00"))
        Mockito.`when`(gachaCostMapper.findAllByGachaId(1L, "gacha_id")).thenReturn(listOf(GachaCost.create(1L, 1L, 0, 0, "2022-01-01 00:00:00")))
        Mockito.`when`(gachaProbabilityMapper.findAllByGachaId(1L, "gacha_id")).thenReturn(listOf(GachaProbability.create(1L, 1L, 100, 0, 0, 0, "2022-01-01 00:00:00")))
        val actual: Gacha = gachaRepository.find(GachaId.create(1L), GachaOrderKey.GACHA_ID) ?: return

        Assertions.assertSame(1L, actual.gachaId.id)
        Assertions.assertTrue(actual.gachaInfo.gachaName.name == "name")
        Assertions.assertTrue(actual.gachaInfo.bannerImage.url == "banner")
        Assertions.assertSame(1, actual.gachaInfo.execCount.count)
        Assertions.assertTrue(actual.gachaCostList[0].costType == CostType.NONE)
        Assertions.assertSame(0, actual.gachaCostList[0].cost.cost)
        Assertions.assertSame(100, actual.gachaProbabilityList[0].probability.probability)
        Assertions.assertTrue(actual.gachaProbabilityList[0].objectType == ObjectType.NONE)
        Assertions.assertSame(0L, actual.gachaProbabilityList[0].objectId.id)
        Assertions.assertSame(0, actual.gachaProbabilityList[0].objectCount.count)
    }

    @Test
    fun findAll() {
        Mockito.`when`(gachaInfoMapper.findAll("gacha_id")).thenReturn(listOf(GachaInfo.create(1L, 1L, "name", "banner", 1, "2022-01-01 00:00:00")))
        Mockito.`when`(gachaCostMapper.findAll("gacha_id")).thenReturn(listOf(GachaCost.create(1L, 1L, 0, 0, "2022-01-01 00:00:00")))
        Mockito.`when`(gachaProbabilityMapper.findAll("gacha_id")).thenReturn(listOf(GachaProbability.create(1L, 1L, 100, 0, 0, 0, "2022-01-01 00:00:00")))
        val actual: List<Gacha> = gachaRepository.findAll(GachaOrderKey.GACHA_ID)

        Assertions.assertSame(1L, actual[0].gachaId.id)
        Assertions.assertTrue(actual[0].gachaInfo.gachaName.name == "name")
        Assertions.assertTrue(actual[0].gachaInfo.bannerImage.url == "banner")
        Assertions.assertSame(1, actual[0].gachaInfo.execCount.count)
        Assertions.assertTrue(actual[0].gachaCostList[0].costType == CostType.NONE)
        Assertions.assertSame(0, actual[0].gachaCostList[0].cost.cost)
        Assertions.assertSame(100, actual[0].gachaProbabilityList[0].probability.probability)
        Assertions.assertTrue(actual[0].gachaProbabilityList[0].objectType == ObjectType.NONE)
        Assertions.assertSame(0L, actual[0].gachaProbabilityList[0].objectId.id)
        Assertions.assertSame(0, actual[0].gachaProbabilityList[0].objectCount.count)
    }
}
