package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CostTest {
    @Test
    fun create() {
        val cost: Cost = Cost.create(100)
        Assertions.assertSame(100, cost.cost)
    }

    @Test
    fun error() {
        val exception: DomainException = assertThrows<DomainException>() {
            Cost.create(-1)
        }
        Assertions.assertSame("Cost is invalid.", exception.message)
    }
}
