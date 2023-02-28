package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.gacha.data.GachaCostData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInfoData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaOutputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaProbabilityData
import com.sireikan.gack.domain.model.gacha.CostType
import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.model.gacha.ObjectType
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
                    when (cost.costType) {
                        CostType.NONE -> 0
                        CostType.GAME_COIN -> 1
                        CostType.PURCHASE_COIN -> 2
                    },
                    cost.cost.cost,
                )
            }.toList(),
            gacha.gachaProbabilityList.stream().map { probability ->
                GachaProbabilityData.create(
                    probability.probability.probability,
                    when (probability.objectType) {
                        ObjectType.NONE -> 0
                        ObjectType.CHARACTER -> 1
                        ObjectType.ITEM -> 2
                        ObjectType.GAME_COIN -> 3
                    },
                    probability.objectId.id,
                    probability.objectCount.count,
                )
            }.toList(),
        )
    }
}
