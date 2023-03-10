package com.sireikan.gack.infrastructure.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaProbabilityLogTest {
    @Test
    fun create() {
        val gachaProbabilityLog: GachaProbabilityLog = GachaProbabilityLog.create(1L, 1L, 100, 0, 1L, 1, "2023-01-01 00:00:00", null)
        Assertions.assertSame(1L, gachaProbabilityLog.id)
        Assertions.assertSame(1L, gachaProbabilityLog.gachaId)
        Assertions.assertSame(100, gachaProbabilityLog.probability)
        Assertions.assertSame(0, gachaProbabilityLog.objectType)
        Assertions.assertSame(1L, gachaProbabilityLog.objectId)
        Assertions.assertSame(1, gachaProbabilityLog.objectCount)
        Assertions.assertSame("2023-01-01 00:00:00", gachaProbabilityLog.created)
        Assertions.assertNull(gachaProbabilityLog.deleted)
    }
}
