package com.sireikan.gack.domain.model.gacha

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaCostTest {
    @Test
    fun create() {
        val gachaCost: GachaCost = GachaCost.create(CostType.NONE, Cost.create(100))
        Assertions.assertSame(CostType.NONE, gachaCost.costType)
        Assertions.assertSame(100, gachaCost.cost.cost)
    }
}
