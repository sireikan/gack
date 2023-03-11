package com.sireikan.gack.application.service.usecase.gacha.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaListOutputDataTest {
    @Test
    fun create() {
        val gachaListOutputData: GachaListOutputData = GachaListOutputData.create(
            listOf(
                GachaOutputData.create(
                    GachaInfoData.create("name", "banner", 1),
                    listOf(GachaCostData.create(1, 1)),
                    listOf(GachaProbabilityData.create(100, 1, 1L, 1)),
                )
            )
        )
        Assertions.assertTrue(gachaListOutputData.gachaOutputDataList[0].gachaInfoData.gachaName == "name")
        Assertions.assertTrue(gachaListOutputData.gachaOutputDataList[0].gachaInfoData.bannerImage == "banner")
        Assertions.assertSame(1, gachaListOutputData.gachaOutputDataList[0].gachaInfoData.execCount)
        Assertions.assertSame(1, gachaListOutputData.gachaOutputDataList[0].gachaCostDataList[0].costType)
        Assertions.assertSame(1, gachaListOutputData.gachaOutputDataList[0].gachaCostDataList[0].cost)
        Assertions.assertSame(100, gachaListOutputData.gachaOutputDataList[0].gachaProbabilityDataList[0].probability)
        Assertions.assertSame(1, gachaListOutputData.gachaOutputDataList[0].gachaProbabilityDataList[0].objectType)
        Assertions.assertSame(1L, gachaListOutputData.gachaOutputDataList[0].gachaProbabilityDataList[0].objectId)
        Assertions.assertSame(1, gachaListOutputData.gachaOutputDataList[0].gachaProbabilityDataList[0].objectCount)
    }
}
