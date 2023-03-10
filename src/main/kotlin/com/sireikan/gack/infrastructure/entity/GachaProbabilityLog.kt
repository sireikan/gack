package com.sireikan.gack.infrastructure.entity

import com.sireikan.gack.infrastructure.error.RepositoryException

class GachaProbabilityLog private constructor(
    val id: Long,
    val gachaId: Long,
    val probability: Int,
    val objectType: Int,
    val objectId: Long,
    val objectCount: Int,
    val created: String,
    val deleted: String?,
) {
    companion object {
        fun create(id: Long, gachaId: Long, probability: Int, objectType: Int, objectId: Long, objectCount: Int, created: String, deleted: String?): GachaProbabilityLog {
            return GachaProbabilityLog(id, gachaId, probability, objectType, objectId, objectCount, created, deleted)
        }
    }
}
