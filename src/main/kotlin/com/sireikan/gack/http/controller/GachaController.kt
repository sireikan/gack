package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.usecase.error.UseCaseException
import com.sireikan.gack.application.service.usecase.gacha.CreateGachaUseCase
import com.sireikan.gack.application.service.usecase.gacha.DeleteGachaUseCase
import com.sireikan.gack.application.service.usecase.gacha.GetGachaListUseCase
import com.sireikan.gack.application.service.usecase.gacha.GetGachaUseCase
import com.sireikan.gack.application.service.usecase.gacha.UpdateGachaCostUseCase
import com.sireikan.gack.application.service.usecase.gacha.UpdateGachaInfoUseCase
import com.sireikan.gack.application.service.usecase.gacha.UpdateGachaProbabilityUseCase
import com.sireikan.gack.application.service.usecase.gacha.UpdateGachaUseCase
import com.sireikan.gack.application.service.usecase.gacha.data.GachaCostData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaCreateData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInfoData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaInputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaListOutputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaOutputData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaProbabilityData
import com.sireikan.gack.application.service.usecase.gacha.data.GachaUpdateData
import com.sireikan.gack.openapi.generated.controller.GachaApi
import com.sireikan.gack.openapi.generated.model.GachaCostResponse
import com.sireikan.gack.openapi.generated.model.GachaInfoRequest
import com.sireikan.gack.openapi.generated.model.GachaInfoResponse
import com.sireikan.gack.openapi.generated.model.GachaPostRequest
import com.sireikan.gack.openapi.generated.model.GachaProbabilityResponse
import com.sireikan.gack.openapi.generated.model.GachaPutRequest
import com.sireikan.gack.openapi.generated.model.GachaResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaCostRequest
import com.sireikan.gack.openapi.generated.model.MultipleGachaCostResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaProbabilityRequest
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
    @Autowired val updateGachaUseCase: UpdateGachaUseCase,
    @Autowired val updateGachaInfoUseCase: UpdateGachaInfoUseCase,
    @Autowired val updateGachaCostUseCase: UpdateGachaCostUseCase,
    @Autowired val updateGachaProbabilityUseCase: UpdateGachaProbabilityUseCase,
    @Autowired val deleteGachaUseCase: DeleteGachaUseCase,
) : GachaApi {
    override fun getGacha(): ResponseEntity<MultipleGachaResponse> {
        try {
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
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun getGachaId(id: Int): ResponseEntity<GachaResponse> {
        try {
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
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun postGacha(gachaPostRequest: GachaPostRequest?): ResponseEntity<Unit> {
        if (gachaPostRequest == null) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        try {
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
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun putGachaId(id: Int, gachaPutRequest: GachaPutRequest?): ResponseEntity<Unit> {
        if (gachaPutRequest == null) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        try {
            updateGachaUseCase.execute(
                GachaUpdateData.create(
                    id.toLong(),
                    GachaInfoData.create(
                        gachaPutRequest.gachaInfo.gachaName,
                        gachaPutRequest.gachaInfo.bannerImage,
                        gachaPutRequest.gachaInfo.execCount,
                    ),
                    gachaPutRequest.gachaCosts.costs.stream().map { cost ->
                        GachaCostData.create(
                            cost.costType,
                            cost.cost,
                        )
                    }.toList(),
                    gachaPutRequest.gachaProbabilities.probabilities.stream().map { probability ->
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
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun putGachaGachaIdGachaInfoId(
        gachaId: Int,
        gachaInfoRequest: GachaInfoRequest?,
    ): ResponseEntity<Unit> {
        if (gachaInfoRequest == null) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        try {
            updateGachaInfoUseCase.execute(
                GachaInputData.create(gachaId.toLong()),
                GachaInfoData.create(
                    gachaInfoRequest.gachaName,
                    gachaInfoRequest.bannerImage,
                    gachaInfoRequest.execCount,
                ),
            )
            return ResponseEntity(
                HttpStatus.OK,
            )
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun putGachaGachaIdGachaCostId(
        gachaId: Int,
        multipleGachaCostRequest: MultipleGachaCostRequest?,
    ): ResponseEntity<Unit> {
        if (multipleGachaCostRequest == null || multipleGachaCostRequest.costs.isEmpty()) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        try {
            updateGachaCostUseCase.execute(
                GachaInputData.create(gachaId.toLong()),
                multipleGachaCostRequest.costs.stream().map { cost ->
                    GachaCostData.create(
                        cost.costType,
                        cost.cost,
                    )
                }.toList(),
            )
            return ResponseEntity(
                HttpStatus.OK,
            )
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun putGachaGachaIdGachaProbabilityId(
        gachaId: Int,
        multipleGachaProbabilityRequest: MultipleGachaProbabilityRequest?,
    ): ResponseEntity<Unit> {
        if (multipleGachaProbabilityRequest == null || multipleGachaProbabilityRequest.probabilities.isEmpty()) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        try {
            updateGachaProbabilityUseCase.execute(
                GachaInputData.create(gachaId.toLong()),
                multipleGachaProbabilityRequest.probabilities.stream().map { probability ->
                    GachaProbabilityData.create(
                        probability.probability,
                        probability.objectType,
                        probability.objectId,
                        probability.objectCount,
                    )
                }.toList(),
            )
            return ResponseEntity(
                HttpStatus.OK,
            )
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun deleteGachaId(id: Int): ResponseEntity<Unit> {
        try {
            deleteGachaUseCase.execute(id.toLong())
            return ResponseEntity(
                HttpStatus.OK,
            )
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }
}
