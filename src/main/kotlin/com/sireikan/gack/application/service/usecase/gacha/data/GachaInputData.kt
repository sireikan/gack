package com.sireikan.gack.application.service.usecase.gacha.data

class GachaInputData private constructor(
    val gachaId: Long,
) {
    companion object {
        fun create(gachaId: Long): GachaInputData {
            return GachaInputData(gachaId)
        }
    }
}
