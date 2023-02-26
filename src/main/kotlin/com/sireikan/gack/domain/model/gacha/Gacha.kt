package com.sireikan.gack.domain.model.gacha

class Gacha private constructor(
    val gachaId: GachaId,
    val gachaInfo: GachaInfo,
    val gachaCostList: List<GachaCost>,
    val gachaProbabilityList: List<GachaProbability>,
) {
    companion object {
        fun create(gachaId: GachaId, gachaInfo: GachaInfo): Gacha {
            return Gacha(gachaId, gachaInfo, emptyList(), emptyList())
        }

        fun reconstruct(gachaId: GachaId, gachaInfo: GachaInfo, gachaCostList: List<GachaCost>, gachaProbabilityList: List<GachaProbability>): Gacha {
            return Gacha(gachaId, gachaInfo, gachaCostList, gachaProbabilityList)
        }
    }
}
