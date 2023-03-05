package com.sireikan.gack.infrastructure.entity

import com.sireikan.gack.infrastructure.error.RepositoryException

class GachaCost private constructor(
    val id: Long,
    val gachaId: Long,
    val costType: Int,
    val cost: Int,
    val created: String,
) {
    companion object {
        fun create(id: Long, gachaId: Long, costType: Int, cost: Int, created: String): GachaCost {
            return GachaCost(id, gachaId, costType, cost, created)
        }
    }

    init {
        if (id < 0) {
            throw RepositoryException("GachaCost is invalid.")
        }
        if (gachaId < 0) {
            throw RepositoryException("GachaCost is invalid.")
        }
        if (cost < 0) {
            throw RepositoryException("GachaCost is invalid.")
        }
    }
}
