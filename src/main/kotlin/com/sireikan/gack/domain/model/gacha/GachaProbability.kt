package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

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
    init {
        if (!listOf<ObjectType>(ObjectType.NONE, ObjectType.CHARACTER, ObjectType.ITEM, ObjectType.GAME_COIN).contains(objectType)) {
            throw DomainException("GachaProbability is invalid.")
        }
    }
}
