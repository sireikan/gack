package com.sireikan.gack.application.service.usecase.gacha.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaCreateDataTest {
    @Test
    fun create() {
        val gachaCreateData: GachaCreateData = GachaCreateData.create(
            1L,
            GachaInfoData.create("name", "banner", 1),
            listOf(GachaCostData.create(1, 1)),
            listOf(GachaProbabilityData.create(100, 1, 1L, 1))
        )
        Assertions.assertSame(1L, gachaCreateData.gachaId)
        Assertions.assertTrue(gachaCreateData.gachaInfoData.gachaName == "name")
        Assertions.assertTrue(gachaCreateData.gachaInfoData.bannerImage == "banner")
        Assertions.assertSame(1, gachaCreateData.gachaInfoData.execCount)
        Assertions.assertSame(1, gachaCreateData.gachaCostDataList[0].costType)
        Assertions.assertSame(1, gachaCreateData.gachaCostDataList[0].cost)
        Assertions.assertSame(100, gachaCreateData.gachaProbabilityDataList[0].probability)
        Assertions.assertSame(1, gachaCreateData.gachaProbabilityDataList[0].objectType)
        Assertions.assertSame(1L, gachaCreateData.gachaProbabilityDataList[0].objectId)
        Assertions.assertSame(1, gachaCreateData.gachaProbabilityDataList[0].objectCount)
    }
}