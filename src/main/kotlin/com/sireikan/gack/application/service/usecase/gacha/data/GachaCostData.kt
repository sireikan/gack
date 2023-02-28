package com.sireikan.gack.application.service.usecase.gacha.data

class GachaCostData private constructor(
    val costType: Int,
    val cost: Int,
) {
    companion object {
        fun create(costType: Int, cost: Int): GachaCostData {
            return GachaCostData(costType, cost)
        }
    }
}
