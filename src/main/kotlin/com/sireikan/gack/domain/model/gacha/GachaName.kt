package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class GachaName private constructor(
    val name: String,
) {
    companion object {
        fun create(name: String): GachaName {
            return GachaName(name)
        }
    }
    init {
        if (name.isBlank()) {
            throw DomainException("GachaName is invalid.")
        }
        if (name.length > 20) {
            throw DomainException("GachaName is longer.")
        }
    }
}
