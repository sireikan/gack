package com.sireikan.gack.domain.model.gacha

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaProbabilityTest {
    @Test
    fun create() {
        val gachaProbability: GachaProbability = GachaProbability.create(Probability.create(100), ObjectType.CHARACTER, ObjectId.create(100), ObjectCount.create(1))
        Assertions.assertSame(100, gachaProbability.probability.probability)
        Assertions.assertSame(ObjectType.CHARACTER, gachaProbability.objectType)
        Assertions.assertSame(100L, gachaProbability.objectId.id)
        Assertions.assertSame(1, gachaProbability.objectCount.count)
    }
}
