package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

data class GachaName (
    val name: String
) {
    init {
        if (name.isBlank() || name.length > 20) {
            throw DomainException("Gacha name is invalid.")
        }
    }
}
