package com.sireikan.gack.domain.model.gacha

class Gacha private constructor(
    val gachaInfo: GachaInfo,
    val gachaCostList: List<GachaCost>,
    val gachaProbabilityList: List<GachaProbability>,
) {
    companion object {
        fun create(gachaInfo: GachaInfo): Gacha {
            return Gacha(gachaInfo, emptyList(), emptyList())
        }

        fun reconstruct(gachaInfo: GachaInfo, gachaCostList: List<GachaCost>, gachaProbabilityList: List<GachaProbability>): Gacha {
            return Gacha(gachaInfo, gachaCostList, gachaProbabilityList)
        }
    }
}
