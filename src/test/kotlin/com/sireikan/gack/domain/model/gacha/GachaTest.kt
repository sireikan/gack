package com.sireikan.gack.domain.model.gacha

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaTest {
    @Test
    fun create() {
        val gacha: Gacha = Gacha.create(GachaId.create(1), GachaInfo.create(GachaName.create("name"), BannerImage.create("htttps://hogehoge.png"), GachaExecCount.create(1)))
        Assertions.assertSame(1L, gacha.gachaId.id)
        Assertions.assertSame("name", gacha.gachaInfo.gachaName.name)
        Assertions.assertSame("htttps://hogehoge.png", gacha.gachaInfo.bannerImage.url)
        Assertions.assertSame(1, gacha.gachaInfo.execCount.count)
    }

    @Test
    fun reconstruct() {
        val gacha: Gacha = Gacha.reconstruct(
            GachaId.create(1),
            GachaInfo.create(GachaName.create("name"), BannerImage.create("htttps://hogehoge.png"), GachaExecCount.create(1)),
            listOf(GachaCost.create(CostType.GAME_COIN, Cost.create(10))),
            listOf(GachaProbability.create(Probability.create(100), ObjectType.CHARACTER, ObjectId.create(1), ObjectCount.create(1))),
        )
        Assertions.assertSame(1L, gacha.gachaId.id)
        Assertions.assertSame("name", gacha.gachaInfo.gachaName.name)
        Assertions.assertSame("htttps://hogehoge.png", gacha.gachaInfo.bannerImage.url)
        Assertions.assertSame(1, gacha.gachaInfo.execCount.count)
        Assertions.assertSame(CostType.GAME_COIN, gacha.gachaCostList[0].costType)
        Assertions.assertSame(10, gacha.gachaCostList[0].cost.cost)
        Assertions.assertSame(100, gacha.gachaProbabilityList[0].probability.probability)
        Assertions.assertSame(ObjectType.CHARACTER, gacha.gachaProbabilityList[0].objectType)
        Assertions.assertSame(1L, gacha.gachaProbabilityList[0].objectId.id)
        Assertions.assertSame(1, gacha.gachaProbabilityList[0].objectCount.count)
    }
}
