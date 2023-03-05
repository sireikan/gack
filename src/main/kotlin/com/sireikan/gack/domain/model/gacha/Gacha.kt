package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class Gacha private constructor(
    val gachaId: GachaId,
    val gachaInfo: GachaInfo,
    val gachaCostList: List<GachaCost>,
    val gachaProbabilityList: List<GachaProbability>,
) {
    companion object {
        fun create(gachaId: GachaId, gachaInfo: GachaInfo, gachaCostList: List<GachaCost>, gachaProbabilityList: List<GachaProbability>): Gacha {
            return Gacha(gachaId, gachaInfo, gachaCostList, gachaProbabilityList)
        }
    }

    init {
        if (gachaCostList.isEmpty()) {
            throw DomainException("Gacha is invalid.")
        }
        if (gachaProbabilityList.isEmpty()) {
            throw DomainException("Gacha is invalid.")
        }
    }
}
