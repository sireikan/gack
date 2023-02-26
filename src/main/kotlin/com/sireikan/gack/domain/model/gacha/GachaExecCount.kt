package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class GachaExecCount private constructor(
    val count: Int,
) {
    companion object {
        fun create(count: Int): GachaExecCount {
            return GachaExecCount(count)
        }
    }
    init {
        if (count <= 0) {
            throw DomainException("GachaExecCount is invalid.")
        }
    }
}
