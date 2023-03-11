package com.sireikan.gack.application.service.usecase.gacha.data

import com.sireikan.gack.application.service.usecase.error.EmptyListUseCaseException
import com.sireikan.gack.application.service.usecase.error.InvalidGachaIdUseCaseException

class GachaCreateData private constructor(
    val gachaId: Long,
    val gachaInfoData: GachaInfoData,
    val gachaCostDataList: List<GachaCostData>,
    val gachaProbabilityDataList: List<GachaProbabilityData>,
) {
    companion object {
        fun create(gachaId: Long, gachaInfoData: GachaInfoData, gachaCostDataList: List<GachaCostData>, gachaProbabilityDataList: List<GachaProbabilityData>): GachaCreateData {
            return GachaCreateData(gachaId, gachaInfoData, gachaCostDataList, gachaProbabilityDataList)
        }
    }
    init {
        if (gachaId < 1L) {
            throw InvalidGachaIdUseCaseException("gachaId is invalid.")
        }
        if (gachaCostDataList.isEmpty()) {
            throw EmptyListUseCaseException("gachaCostDataList is invalid.")
        }
        if (gachaProbabilityDataList.isEmpty()) {
            throw EmptyListUseCaseException("gachaProbabilityDataList is invalid.")
        }
    }
}
