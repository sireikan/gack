package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class ObjectCount private constructor(
    val count: Int,
) {
    companion object {
        fun create(count: Int): ObjectCount {
            return ObjectCount(count)
        }
    }
    init {
        if (count < 0) {
            throw DomainException("ObjectCount is invalid.")
        }
    }
}
