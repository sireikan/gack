package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

enum class CostType(val value: Int) {
    NONE(0),
    GAME_COIN(1),
    PURCHASE_COIN(2),
    ;

    companion object {
        fun from(value: Int): CostType {
            for (item in enumValues<CostType>()) {
                if (item.value == value) {
                    return item
                }
            }
            throw DomainException("CostType is invalid.")
        }
    }
}
