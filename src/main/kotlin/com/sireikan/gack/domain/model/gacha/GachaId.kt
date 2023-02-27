package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class GachaId private constructor(
    val id: Long,
) {
    companion object {
        fun create(id: Long): GachaId {
            return GachaId(id)
        }
    }
    init {
        if (id <= 0) {
            throw DomainException("GachaId is invalid.")
        }
    }
}
