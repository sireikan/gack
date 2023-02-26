package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class Probability private constructor(
    val probability: Int,
) {
    companion object {
        fun create(probability: Int): Probability {
            return Probability(probability)
        }
    }
    init {
        if (probability <= 0) {
            throw DomainException("Probability is invalid.")
        }
    }
}
