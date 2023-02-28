package com.sireikan.gack.application.service.usecase.gacha.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaInfoDataTest {
    @Test
    fun create() {
        val gachaInfoData: GachaInfoData = GachaInfoData.create("name", "banner", 1)
        Assertions.assertTrue(gachaInfoData.gachaName == "name")
        Assertions.assertTrue(gachaInfoData.bannerImage == "banner")
        Assertions.assertSame(1, gachaInfoData.execCount)
    }
}
