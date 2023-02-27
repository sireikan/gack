package com.sireikan.gack.domain.repository

import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.model.gacha.GachaId

interface GachaRepository {
    fun find(gachaId: GachaId, gachaOrderKey: GachaOrderKey): Gacha
    fun findAll(gachaOrderKey: GachaOrderKey): List<Gacha>
}
