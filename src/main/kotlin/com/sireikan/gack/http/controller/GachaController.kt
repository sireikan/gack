package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.usecase.gacha.CreateGachaUseCase
import com.sireikan.gack.application.service.usecase.gacha.GetGachaListUseCase
import com.sireikan.gack.application.service.usecase.gacha.GetGachaUseCase
import com.sireikan.gack.application.service.usecase.gacha.data.GachaCostData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaCreateData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInfoData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaListOutputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaOutputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaProbabilityData
import com.sireikan.gack.openapi.generated.controller.GachaApi
import com.sireikan.gack.openapi.generated.model.GachaCostResponse
import com.sireikan.gack.openapi.generated.model.GachaInfoResponse
import com.sireikan.gack.openapi.generated.model.GachaPostRequest
import com.sireikan.gack.openapi.generated.model.GachaProbabilityResponse
import com.sireikan.gack.openapi.generated.model.GachaPutRequest
import com.sireikan.gack.openapi.generated.model.GachaResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaCostResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaProbabilityResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class GachaController(
    @Autowired val getGachaListUseCase: GetGachaListUseCase,
    @Autowired val getGachaUseCase: GetGachaUseCase,
    @Autowired val createGachaUseCase: CreateGachaUseCase,
) : GachaApi {
    override fun getGacha(): ResponseEntity<MultipleGachaResponse> {
        val gachaListOutputData: GachaListOutputData = getGachaListUseCase.execute()
        return ResponseEntity(
            MultipleGachaResponse(
                gachaListOutputData.gachaOutputDataList.stream().map { gachaOutputData ->
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
                    )
                }.toList(),
            ),
            HttpStatus.OK,
        )
    }

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
        createGachaUseCase.execute(
            GachaCreateData.create(
                gachaPostRequest.gachaId,
                GachaInfoData.create(
                    gachaPostRequest.gachaInfo.gachaName,
                    gachaPostRequest.gachaInfo.bannerImage,
                    gachaPostRequest.gachaInfo.execCount,
                ),
                gachaPostRequest.gachaCosts.costs.stream().map { cost ->
                    GachaCostData.create(
                        cost.costType,
                        cost.cost,
                    )
                }.toList(),
                gachaPostRequest.gachaProbabilities.probabilities.stream().map { probability ->
                    GachaProbabilityData.create(
                        probability.probability,
                        probability.objectType,
                        probability.objectId,
                        probability.objectCount,
                    )
                }.toList(),
            ),
        )
        return ResponseEntity(
            HttpStatus.OK,
        )
    }

    override fun putGachaId(id: String, gachaPutRequest: GachaPutRequest?): ResponseEntity<Unit> {
        return super.putGachaId(id, gachaPutRequest)
    }

    override fun deleteGachaId(id: String): ResponseEntity<Unit> {
        return super.deleteGachaId(id)
    }
}
