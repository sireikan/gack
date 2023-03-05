package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.gacha.data.GachaCreateData
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
import com.sireikan.gack.domain.repository.GachaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CreateGachaUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(gachaCreateData: GachaCreateData) {
        gachaRepository.insert(
            Gacha.create(
                GachaId.create(gachaCreateData.gachaId),
                GachaInfo.create(
                    GachaName.create(gachaCreateData.gachaInfoData.gachaName),
                    BannerImage.create(gachaCreateData.gachaInfoData.bannerImage),
                    GachaExecCount.create(gachaCreateData.gachaInfoData.execCount),
                ),
                gachaCreateData.gachaCostDataList.stream().map { cost ->
                    GachaCost.create(
                        CostType.from(cost.costType),
                        Cost.create(cost.cost),
                    )
                }.toList(),
                gachaCreateData.gachaProbabilityDataList.stream().map { probability ->
                    GachaProbability.create(
                        Probability.create(probability.probability),
                        ObjectType.from(probability.objectType),
                        ObjectId.create(probability.objectId),
                        ObjectCount.create(probability.objectCount),
                    )
                }.toList(),
            ),
        )
    }
}
