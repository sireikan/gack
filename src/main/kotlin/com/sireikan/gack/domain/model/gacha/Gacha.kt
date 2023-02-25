package com.sireikan.gack.domain.model.gacha

data class Gacha (
    val gachaInfo: GachaInfo,
    val gachaCostList: List<GachaCost>,
    val gachaProbability: List<GachaProbability>
)
