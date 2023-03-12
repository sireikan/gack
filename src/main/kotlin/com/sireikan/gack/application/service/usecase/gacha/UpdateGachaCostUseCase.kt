package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.error.GachaNotFoundUseCaseException
import com.sireikan.gack.application.service.usecase.gacha.data.GachaCostData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInputData
import com.sireikan.gack.domain.model.gacha.Cost
import com.sireikan.gack.domain.model.gacha.CostType
import com.sireikan.gack.domain.model.gacha.GachaCost
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.repository.GachaOrderKey
import com.sireikan.gack.domain.repository.GachaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class UpdateGachaCostUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(gachaInputData: GachaInputData, gachaCostDataList: List<GachaCostData>) {
        gachaRepository.find(GachaId.create(gachaInputData.gachaId), GachaOrderKey.GACHA_ID)
            ?: throw GachaNotFoundUseCaseException("Gacha is not found.")
        gachaRepository.updateGachaCost(
            GachaId.create(gachaInputData.gachaId),
            gachaCostDataList.stream().map { cost ->
                GachaCost.create(
                    CostType.from(cost.costType),
                    Cost.create(cost.cost),
                )
            }.toList(),
        )
    }
}
