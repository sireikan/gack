package com.sireikan.gack.domain.model.gacha

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProbabilityTest {
    @Test
    fun create() {
        val probability: Probability = Probability(100)
        Assertions.assertSame(100, probability.probability)
    }
}