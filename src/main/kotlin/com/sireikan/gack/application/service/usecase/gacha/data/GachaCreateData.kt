package com.sireikan.gack.application.service.usecase.gacha.data

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
}
