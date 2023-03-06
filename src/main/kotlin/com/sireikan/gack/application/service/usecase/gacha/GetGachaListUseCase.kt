package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.gacha.data.*
import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.repository.GachaOrderKey
import com.sireikan.gack.domain.repository.GachaRepository

class GetGachaListUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(): GachaListOutputData {
        val gachaList: List<Gacha> = gachaRepository.findAll(GachaOrderKey.GACHA_ID)
        return GachaListOutputData.create(
            gachaList.stream().map { gacha ->
                GachaOutputData.create(
                    GachaInfoData.create(
                        gacha.gachaInfo.gachaName.name,
                        gacha.gachaInfo.bannerImage.url,
                        gacha.gachaInfo.execCount.count,
                    ),
                    gacha.gachaCostList.stream().map { cost ->
                        GachaCostData.create(
                            cost.costType.value,
                            cost.cost.cost,
                        )
                    }.toList(),
                    gacha.gachaProbabilityList.stream().map { probability ->
                        GachaProbabilityData.create(
                            probability.probability.probability,
                            probability.objectType.value,
                            probability.objectId.id,
                            probability.objectCount.count,
                        )
                    }.toList(),
                )
            }.toList()
        )
    }
}
