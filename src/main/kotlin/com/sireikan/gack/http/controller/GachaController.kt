package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.usecase.gacha.CreateGachaUseCase
import com.sireikan.gack.application.service.usecase.gacha.GetGachaUseCase
import com.sireikan.gack.application.service.usecase.gacha.data.GachaCreateData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInfoData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaOutputData
import com.sireikan.gack.openapi.generated.controller.GachaApi
import com.sireikan.gack.openapi.generated.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class GachaController(
    @Autowired val getGachaUseCase: GetGachaUseCase,
    @Autowired val createGachaUseCase: CreateGachaUseCase,
) : GachaApi {
    override fun getGachaId(id: String): ResponseEntity<GachaResponse> {
        val gachaOutputData: GachaOutputData = getGachaUseCase.execute(GachaInputData.create(id.toLong())) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(
            GachaResponse(
                GachaInfoResponse(
                    gachaOutputData.gachaInfoData.gachaName,
                    gachaOutputData.gachaInfoData.bannerImage,
                    gachaOutputData.gachaInfoData.execCount,
                ),
                MultipleGachaCostResponse(
                    gachaOutputData.gachaCostDataList.stream().map { cost ->
                        GachaCostResponse(
                            cost.costType,
                            cost.cost,
                        )
                    }.toList(),
                ),
                MultipleGachaProbabilityResponse(
                    gachaOutputData.gachaProbabilityDataList.stream().map { probability ->
                        GachaProbabilityResponse(
                            probability.probability,
                            probability.objectType,
                            probability.objectId,
                            probability.objectCount,
                        )
                    }.toList(),
                ),
            ),
            HttpStatus.OK,
        )
    }

    override fun postGacha(gachaPostRequest: GachaPostRequest?): ResponseEntity<Unit> {
        if (gachaPostRequest == null) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        createGachaUseCase.execute(GachaCreateData.create(
            gachaPostRequest.gachaId,
            GachaInfoData.create(
                gachaPostRequest.gachaInfo.gachaName,
                gachaPostRequest.gachaInfo.bannerImage,
                gachaPostRequest.gachaInfo.execCount
            ),
            listOf(),
            listOf()
        ))
        return ResponseEntity(
            HttpStatus.OK,
        )
    }
}
