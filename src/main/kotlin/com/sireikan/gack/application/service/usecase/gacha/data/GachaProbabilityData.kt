package com.sireikan.gack.application.service.usecase.gacha.data

class GachaProbabilityData private constructor(
    val probability: Int,
    val objectType: Int,
    val objectId: Long,
    val objectCount: Int,
) {
    companion object {
        fun create(probability: Int, objectType: Int, objectId: Long, objectCount: Int): GachaProbabilityData {
            return GachaProbabilityData(probability, objectType, objectId, objectCount)
        }
    }
}
