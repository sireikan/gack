package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class GachaCost private constructor(
    val costType: CostType,
    val cost: Cost,
) {
    companion object {
        fun create(costType: CostType, cost: Cost): GachaCost {
            return GachaCost(costType, cost)
        }
    }
    init {
        if (!listOf<CostType>(CostType.NONE, CostType.GAME_COIN, CostType.PURCHASE_COIN).contains(costType)) {
            throw DomainException("GachaCost is invalid.")
        }
    }
}
