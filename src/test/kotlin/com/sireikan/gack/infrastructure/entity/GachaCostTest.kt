package com.sireikan.gack.infrastructure.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaCostTest {
    @Test
    fun create() {
        val gachaCost: GachaCost = GachaCost.create(1L, 1L, 1, 1, "2023-01-01 00:00:00")
        Assertions.assertSame(1L, gachaCost.id)
        Assertions.assertSame(1L, gachaCost.gachaId)
        Assertions.assertSame(1, gachaCost.costType)
        Assertions.assertSame(1, gachaCost.cost)
        Assertions.assertSame("2023-01-01 00:00:00", gachaCost.created)
    }
}
