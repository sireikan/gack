package com.sireikan.gack.application.service.usecase.gacha

import com.sireikan.gack.application.service.usecase.error.GachaNotFoundUseCaseException
import com.sireikan.gack.application.service.usecase.gacha.data.GachaUpdateData
import com.sireikan.gack.domain.model.gacha.*
import com.sireikan.gack.domain.repository.GachaOrderKey
import com.sireikan.gack.domain.repository.GachaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class DeleteGachaUseCase(
    private val gachaRepository: GachaRepository,
) {
    fun execute(gachaId: Long) {
        val gacha: Gacha? = gachaRepository.find(GachaId.create(gachaId), GachaOrderKey.GACHA_ID)
//            ?: throw GachaNotFoundUseCaseException("Gacha is not found.")
        gachaRepository.delete(GachaId.create(gachaId))
    }
}
