package com.sireikan.gack.domain.repository

import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.model.gacha.GachaId

interface GachaRepository {
    fun find(gachaId: GachaId): Gacha
    fun findAll(): List<Gacha>
    fun insert(gacha: Gacha)
    fun update(gacha: Gacha)
    fun delete(gachaId: GachaId)
}
