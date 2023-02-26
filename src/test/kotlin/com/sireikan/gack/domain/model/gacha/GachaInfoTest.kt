package com.sireikan.gack.domain.model.gacha

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaInfoTest {
    @Test
    fun create() {
        val gachaInfo: GachaInfo = GachaInfo.create(GachaName.create("gacha_name"), BannerImage.create("https://hogehoge.png"), GachaExecCount.create(1))
        Assertions.assertSame("gacha_name", gachaInfo.gachaName.name)
        Assertions.assertSame("https://hogehoge.png", gachaInfo.bannerImage.url)
        Assertions.assertSame(1, gachaInfo.execCount.count)
    }
}
