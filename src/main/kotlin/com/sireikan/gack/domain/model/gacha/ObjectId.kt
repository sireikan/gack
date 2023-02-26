package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

data class ObjectId(
    val id: Long,
) {
    init {
        if (id < 0) {
            throw DomainException("ObjectId is invalid.")
        }
    }
}
