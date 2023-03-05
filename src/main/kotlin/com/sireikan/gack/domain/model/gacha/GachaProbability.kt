package com.sireikan.gack.domain.model.gacha

class GachaProbability private constructor(
    val probability: Probability,
    val objectType: ObjectType,
    val objectId: ObjectId,
    val objectCount: ObjectCount,
) {
    companion object {
        fun create(probability: Probability, objectType: ObjectType, objectId: ObjectId, objectCount: ObjectCount): GachaProbability {
            return GachaProbability(probability, objectType, objectId, objectCount)
        }
    }
}
