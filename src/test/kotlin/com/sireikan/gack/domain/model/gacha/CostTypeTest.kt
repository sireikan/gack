package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CostTypeTest {
    @Test
    fun create() {
        val costType: CostType = CostType.from(0)
        Assertions.assertSame(0, costType.value)
    }

    @Test
    fun error() {
        val exception: DomainException = assertThrows<DomainException>() {
            CostType.from(-1)
        }
        Assertions.assertSame("CostType is invalid.", exception.message)
    }
}
