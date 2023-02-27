package com.sireikan.gack.infrastructure.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaProbabilityTest {
    @Test
    fun create() {
        val gachaProbability: GachaProbability = GachaProbability.create(1L, 1L, 100, 0, 1L, 1, "2023-01-01 00:00:00")
        Assertions.assertSame(1L, gachaProbability.id)
        Assertions.assertSame(1L, gachaProbability.gachaId)
        Assertions.assertSame(100, gachaProbability.probability)
        Assertions.assertSame(0, gachaProbability.objectType)
        Assertions.assertSame(1L, gachaProbability.objectId)
        Assertions.assertSame(1, gachaProbability.objectCount)
        Assertions.assertSame("2023-01-01 00:00:00", gachaProbability.created)
    }
}
