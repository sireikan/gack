package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.gacha.data.GachaCostData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInfoData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaOutputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaProbabilityData
import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.repository.GachaOrderKey
import com.sireikan.gack.domain.repository.GachaRepository
import org.springframework.stereotype.Service

@Service
class GetGachaUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(gachaInputData: GachaInputData): GachaOutputData? {
        val gacha: Gacha = gachaRepository.find(GachaId.create(gachaInputData.gachaId), GachaOrderKey.GACHA_ID) ?: return null
        return GachaOutputData.create(
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
    }
}
