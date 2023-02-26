package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class ObjectId private constructor(
    val id: Long,
) {
    companion object {
        fun create(id: Long): ObjectId {
            return ObjectId(id)
        }
    }
    init {
        if (id < 0) {
            throw DomainException("ObjectId is invalid.")
        }
    }
}
