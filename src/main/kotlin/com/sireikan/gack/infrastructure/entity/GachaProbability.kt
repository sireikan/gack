package com.sireikan.gack.infrastructure.entity

class GachaProbability private constructor(
    val id: Long,
    val gachaId: Long,
    val probability: Int,
    val objectType: Int,
    val objectId: Long,
    val objectCount: Int,
    val created: String,
) {
    companion object {
        fun create(id: Long, gachaId: Long, probability: Int, objectType: Int, objectId: Long, objectCount: Int, created: String): GachaProbability {
            return GachaProbability(id, gachaId, probability, objectType, objectId, objectCount, created)
        }
    }
}
