package com.sireikan.gack.application.service.usecase.gacha.data

class GachaOutputData private constructor(
    val gachaInfoData: GachaInfoData,
    val gachaCostDataList: List<GachaCostData>,
    val gachaProbabilityDataList: List<GachaProbabilityData>,
) {
    companion object {
        fun create(gachaInfoData: GachaInfoData, gachaCostDataList: List<GachaCostData>, gachaProbabilityDataList: List<GachaProbabilityData>): GachaOutputData {
            return GachaOutputData(gachaInfoData, gachaCostDataList, gachaProbabilityDataList)
        }
    }
}
