package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProbabilityTest {
    @Test
    fun create() {
        val probability: Probability = Probability.create(100)
        Assertions.assertSame(100, probability.probability)
    }

    @Test
    fun error() {
        val exception: DomainException = assertThrows<DomainException>() {
            Probability.create(0)
        }
        Assertions.assertSame("Probability is invalid.", exception.message)
    }
}
