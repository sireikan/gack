package com.sireikan.gack.application.service.usecase.gacha.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaProbabilityDataTest {
    @Test
    fun create() {
        val gachaProbabilityData: GachaProbabilityData = GachaProbabilityData.create(100, 1, 1L, 1)
        Assertions.assertSame(100, gachaProbabilityData.probability)
        Assertions.assertSame(1, gachaProbabilityData.objectType)
        Assertions.assertSame(1L, gachaProbabilityData.objectId)
        Assertions.assertSame(1, gachaProbabilityData.objectCount)
    }
}
