package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.error.GachaNotFoundUseCaseException
import com.sireikan.gack.application.service.usecase.gacha.data.GachaUpdateData
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
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class UpdateGachaUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(gachaUpdateData: GachaUpdateData) {
        gachaRepository.find(GachaId.create(gachaUpdateData.gachaId), GachaOrderKey.GACHA_ID)
            ?: throw GachaNotFoundUseCaseException("Gacha is not found.")
        gachaRepository.update(
            Gacha.create(
                GachaId.create(gachaUpdateData.gachaId),
                GachaInfo.create(
                    GachaName.create(gachaUpdateData.gachaInfoData.gachaName),
                    BannerImage.create(gachaUpdateData.gachaInfoData.bannerImage),
                    GachaExecCount.create(gachaUpdateData.gachaInfoData.execCount),
                ),
                gachaUpdateData.gachaCostDataList.stream().map { cost ->
                    GachaCost.create(
                        CostType.from(cost.costType),
                        Cost.create(cost.cost),
                    )
                }.toList(),
                gachaUpdateData.gachaProbabilityDataList.stream().map { probability ->
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
