package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.domain.model.gacha.CostType
import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.model.gacha.ObjectType
import com.sireikan.gack.domain.repository.GachaOrderKey
import com.sireikan.gack.domain.repository.GachaRepository
import com.sireikan.gack.openapi.generated.model.GachaCostResponse
import com.sireikan.gack.openapi.generated.model.GachaInfoResponse
import com.sireikan.gack.openapi.generated.model.GachaProbabilityResponse
import com.sireikan.gack.openapi.generated.model.GachaRequest
import com.sireikan.gack.openapi.generated.model.GachaResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaCostResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaProbabilityResponse
import org.springframework.stereotype.Service

@Service
class GetGachaUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(gachaRequest: GachaRequest): GachaResponse {
        val gacha: Gacha = gachaRepository.find(GachaId.create(gachaRequest.gachaId), GachaOrderKey.GACHA_ID)
        return GachaResponse(
            GachaInfoResponse(
                gacha.gachaInfo.gachaName.name,
                gacha.gachaInfo.bannerImage.url,
                gacha.gachaInfo.execCount.count,
            ),
            MultipleGachaCostResponse(
                gacha.gachaCostList.stream().map { cost ->
                    GachaCostResponse(
                        when (cost.costType) {
                            CostType.NONE -> 0
                            CostType.GAME_COIN -> 1
                            CostType.PURCHASE_COIN -> 2
                        },
                        cost.cost.cost,
                    )
                }.toList(),
            ),
            MultipleGachaProbabilityResponse(
                gacha.gachaProbabilityList.stream().map { probability ->
                    GachaProbabilityResponse(
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
            ),
        )
    }
}
