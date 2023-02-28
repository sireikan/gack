package com.sireikan.gack.application.service.usecase.gacha.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaOutputDataTest {
    @Test
    fun create() {
        val gachaOutputData: GachaOutputData = GachaOutputData.create(
            GachaInfoData.create("name", "banner", 1),
            listOf(GachaCostData.create(1, 1)),
            listOf(GachaProbabilityData.create(100, 1, 1L, 1)),
        )
        Assertions.assertTrue(gachaOutputData.gachaInfoData.gachaName == "name")
        Assertions.assertTrue(gachaOutputData.gachaInfoData.bannerImage == "banner")
        Assertions.assertSame(1, gachaOutputData.gachaInfoData.execCount)
        Assertions.assertSame(1, gachaOutputData.gachaCostDataList[0].costType)
        Assertions.assertSame(1, gachaOutputData.gachaCostDataList[0].cost)
        Assertions.assertSame(100, gachaOutputData.gachaProbabilityDataList[0].probability)
        Assertions.assertSame(1, gachaOutputData.gachaProbabilityDataList[0].objectType)
        Assertions.assertSame(1L, gachaOutputData.gachaProbabilityDataList[0].objectId)
        Assertions.assertSame(1, gachaOutputData.gachaProbabilityDataList[0].objectCount)
    }
}
