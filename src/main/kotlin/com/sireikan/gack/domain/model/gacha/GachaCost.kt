package com.sireikan.gack.domain.model.gacha

class GachaCost private constructor(
    val costType: CostType,
    val cost: Cost,
) {
    companion object {
        fun create(costType: CostType, cost: Cost): GachaCost {
            return GachaCost(costType, cost)
        }
    }
}
