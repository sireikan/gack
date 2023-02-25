package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

data class GachaExecCount (
    val count: Int
) {
    init {
        if (count <= 0) {
            throw DomainException("GachaExecCount is invalid.")
        }
    }
}
