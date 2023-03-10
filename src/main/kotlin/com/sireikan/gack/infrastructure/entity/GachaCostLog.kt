package com.sireikan.gack.infrastructure.entity

import com.sireikan.gack.infrastructure.error.RepositoryException

class GachaCostLog private constructor(
    val gachaId: Long,
    val costType: Int,
    val cost: Int,
    val created: String,
    val deleted: String?,
) {
    companion object {
        fun create(gachaId: Long, costType: Int, cost: Int, created: String, deleted: String?): GachaCostLog {
            return GachaCostLog(gachaId, costType, cost, created, deleted)
        }
    }

    init {
        if (gachaId < 0) {
            throw RepositoryException("GachaCostLog is invalid.")
        }
        if (cost < 0) {
            throw RepositoryException("GachaCostLog is invalid.")
        }
    }
}
