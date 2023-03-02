package com.sireikan.gack.application.service.usecase.gacha.data

import com.sireikan.gack.application.service.usecase.error.UseCaseException

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
            throw UseCaseException("gachaId is invalid.")
        }
        if (gachaCostDataList.isEmpty()) {
            throw UseCaseException("gachaCostDataList is invalid.")
        }
        if (gachaProbabilityDataList.isEmpty()) {
            throw UseCaseException("gachaProbabilityDataList is invalid.")
        }
    }
}
