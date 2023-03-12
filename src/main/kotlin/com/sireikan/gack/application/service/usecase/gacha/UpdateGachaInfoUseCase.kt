package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.error.GachaNotFoundUseCaseException
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInfoData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInputData
import com.sireikan.gack.domain.model.gacha.BannerImage
import com.sireikan.gack.domain.model.gacha.GachaExecCount
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.model.gacha.GachaInfo
import com.sireikan.gack.domain.model.gacha.GachaName
import com.sireikan.gack.domain.repository.GachaOrderKey
import com.sireikan.gack.domain.repository.GachaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class UpdateGachaInfoUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(gachaInputData: GachaInputData, gachaInfoData: GachaInfoData) {
        gachaRepository.find(GachaId.create(gachaInputData.gachaId), GachaOrderKey.GACHA_ID)
            ?: throw GachaNotFoundUseCaseException("Gacha is not found.")
        gachaRepository.updateGachaInfo(
            GachaId.create(gachaInputData.gachaId),
            GachaInfo.create(
                GachaName.create(gachaInfoData.gachaName),
                BannerImage.create(gachaInfoData.bannerImage),
                GachaExecCount.create(gachaInfoData.execCount),
            ),
        )
    }
}
