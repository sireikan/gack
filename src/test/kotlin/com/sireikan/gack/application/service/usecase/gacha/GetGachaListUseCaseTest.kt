package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.domain.model.gacha.BannerImage
import com.sireikan.gack.domain.model.gacha.Cost
import com.sireikan.gack.domain.model.gacha.CostType
import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.model.gacha.GachaCost
import com.sireikan.gack.domain.model.gacha.GachaExecCount
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.model.gacha.GachaInfo
import com.sireikan.gack.domain.model.gacha.GachaName
import com.sireikan.gack.domain.model.gacha.GachaProbability
import com.sireikan.gack.domain.model.gacha.ObjectCount
import com.sireikan.gack.domain.model.gacha.ObjectId
import com.sireikan.gack.domain.model.gacha.ObjectType
import com.sireikan.gack.domain.model.gacha.Probability
import com.sireikan.gack.domain.repository.GachaOrderKey
import com.sireikan.gack.domain.repository.GachaRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GetGachaListUseCaseTest {
    private lateinit var gachaRepository: GachaRepository
    private lateinit var getGachaListUseCase: GetGachaListUseCase

    @BeforeEach
    fun setup() {
        gachaRepository = Mockito.mock(GachaRepository::class.java)
        getGachaListUseCase = GetGachaListUseCase(gachaRepository)
    }

    @Test
    fun execute() {
        Mockito.`when`(gachaRepository.findAll(GachaOrderKey.GACHA_ID)).thenReturn(
            listOf(
                Gacha.create(
                    GachaId.create(1L),
                    GachaInfo.create(GachaName.create("name"), BannerImage.create("banner"), GachaExecCount.create(1)),
                    listOf(GachaCost.create(CostType.NONE, Cost.create(0))),
                    listOf(GachaProbability.create(Probability.create(100), ObjectType.NONE, ObjectId.create(1L), ObjectCount.create(1))),
                ),
            ),
        )
        val actual = getGachaListUseCase.execute() ?: return

        Assertions.assertTrue(actual.gachaOutputDataList[0].gachaInfoData.gachaName == "name")
        Assertions.assertTrue(actual.gachaOutputDataList[0].gachaInfoData.bannerImage == "banner")
        Assertions.assertSame(1, actual.gachaOutputDataList[0].gachaInfoData.execCount)
        Assertions.assertSame(0, actual.gachaOutputDataList[0].gachaCostDataList[0].costType)
        Assertions.assertSame(0, actual.gachaOutputDataList[0].gachaCostDataList[0].cost)
        Assertions.assertSame(100, actual.gachaOutputDataList[0].gachaProbabilityDataList[0].probability)
        Assertions.assertSame(0, actual.gachaOutputDataList[0].gachaProbabilityDataList[0].objectType)
        Assertions.assertSame(1L, actual.gachaOutputDataList[0].gachaProbabilityDataList[0].objectId)
        Assertions.assertSame(1, actual.gachaOutputDataList[0].gachaProbabilityDataList[0].objectCount)
    }
}
