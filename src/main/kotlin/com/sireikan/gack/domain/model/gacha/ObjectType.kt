package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

enum class ObjectType(val value: Int) {
    NONE(0),
    CHARACTER(1),
    ITEM(2),
    GAME_COIN(3),
    ;

    companion object {
        fun from(value: Int): ObjectType {
            for (item in enumValues<ObjectType>()) {
                if (item.value == value) {
                    return item
                }
            }
            throw DomainException("ObjectType is invalid.")
        }
    }
}
