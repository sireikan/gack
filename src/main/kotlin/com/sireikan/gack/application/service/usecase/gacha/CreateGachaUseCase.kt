package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.error.UseCaseException
import com.sireikan.gack.application.service.usecase.gacha.data.GachaCreateData
import com.sireikan.gack.domain.model.gacha.*
import com.sireikan.gack.domain.repository.GachaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CreateGachaUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(gachaCreateData: GachaCreateData) {
        gachaRepository.insert(Gacha.reconstruct(
            GachaId.create(gachaCreateData.gachaId),
            GachaInfo.create(
                GachaName.create(gachaCreateData.gachaInfoData.gachaName),
                BannerImage.create(gachaCreateData.gachaInfoData.bannerImage),
                GachaExecCount.create(gachaCreateData.gachaInfoData.execCount)
            ),
            gachaCreateData.gachaCostDataList.stream().map { cost ->
                GachaCost.create(
                    when (cost.costType) {
                        0 -> CostType.NONE
                        1 -> CostType.GAME_COIN
                        2 -> CostType.PURCHASE_COIN
                        else -> throw UseCaseException("GachaCreateData is invalid.")
                    },
                    Cost.create(cost.cost)
                )
            }.toList(),
            gachaCreateData.gachaProbabilityDataList.stream().map { probability ->
                GachaProbability.create(
                    Probability.create(probability.probability),
                    when (probability.objectType) {
                        0 -> ObjectType.NONE
                        1 -> ObjectType.CHARACTER
                        2 -> ObjectType.ITEM
                        3 -> ObjectType.GAME_COIN
                        else -> throw UseCaseException("GachaCreateData is invalid.")
                    },
                    ObjectId.create(probability.objectId),
                    ObjectCount.create(probability.objectCount)
                )
            }.toList()
        ))
    }
}
