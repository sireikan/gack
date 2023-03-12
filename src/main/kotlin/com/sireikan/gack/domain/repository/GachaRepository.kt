package com.sireikan.gack.domain.repository

import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.model.gacha.GachaCost
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.model.gacha.GachaInfo
import com.sireikan.gack.domain.model.gacha.GachaProbability

interface GachaRepository {
    fun find(gachaId: GachaId, gachaOrderKey: GachaOrderKey): Gacha?
    fun findAll(gachaOrderKey: GachaOrderKey): List<Gacha>
    fun insert(gacha: Gacha)
    fun update(gacha: Gacha)
    fun updateGachaInfo(gachaId: GachaId, gachaInfo: GachaInfo)
    fun updateGachaCost(gachaId: GachaId, gachaCostList: List<GachaCost>)
    fun updateGachaProbability(gachaId: GachaId, gachaProbabilityList: List<GachaProbability>)
    fun delete(gachaId: GachaId)
}
