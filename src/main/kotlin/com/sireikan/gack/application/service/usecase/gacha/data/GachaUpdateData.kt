package com.sireikan.gack.application.service.usecase.gacha.data

class GachaUpdateData private constructor(
    val gachaId: Long,
    val gachaInfoData: GachaInfoData,
    val gachaCostDataList: List<GachaCostData>,
    val gachaProbabilityDataList: List<GachaProbabilityData>,
) {
    companion object {
        fun create(gachaId: Long, gachaInfoData: GachaInfoData, gachaCostDataList: List<GachaCostData>, gachaProbabilityDataList: List<GachaProbabilityData>): GachaUpdateData {
            return GachaUpdateData(gachaId, gachaInfoData, gachaCostDataList, gachaProbabilityDataList)
        }
    }
}
