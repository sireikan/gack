package com.sireikan.gack.application.service.usecase.gacha.data

class GachaListOutputData private constructor(
    val gachaOutputDataList: List<GachaOutputData>,
) {
    companion object {
        fun create(gachaOutputDataList: List<GachaOutputData>): GachaListOutputData {
            return GachaListOutputData(gachaOutputDataList)
        }
    }
}
