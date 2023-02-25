package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

data class Cost (
    val cost: Int
) {
    init {
        if (cost < 0) {
            throw DomainException("Cost is invalid.")
        }
    }
}