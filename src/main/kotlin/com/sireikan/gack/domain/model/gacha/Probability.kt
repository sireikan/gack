package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

data class Probability (
    val probability: Int
) {
    init {
        if (probability <= 0) {
            throw DomainException("Probability is invalid.")
        }
    }
}