package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class Cost private constructor(
    val cost: Int,
) {
    companion object {
        fun create(cost: Int): Cost {
            return Cost(cost)
        }
    }
    init {
        if (cost < 0) {
            throw DomainException("Cost is invalid.")
        }
    }
}
