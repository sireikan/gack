package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.gacha.data.GachaInputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaProbabilityData
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.model.gacha.GachaProbability
import com.sireikan.gack.domain.model.gacha.ObjectCount
import com.sireikan.gack.domain.model.gacha.ObjectId
import com.sireikan.gack.domain.model.gacha.ObjectType
import com.sireikan.gack.domain.model.gacha.Probability
import com.sireikan.gack.domain.repository.GachaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class UpdateGachaProbabilityUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(gachaInputData: GachaInputData, gachaProbabilityDataList: List<GachaProbabilityData>) {
        gachaRepository.updateGachaProbability(
            GachaId.create(gachaInputData.gachaId),
            gachaProbabilityDataList.stream().map { probability ->
                GachaProbability.create(
                    Probability.create(probability.probability),
                    ObjectType.from(probability.objectType),
                    ObjectId.create(probability.objectId),
                    ObjectCount.create(probability.objectCount),
                )
            }.toList(),
        )
    }
}
