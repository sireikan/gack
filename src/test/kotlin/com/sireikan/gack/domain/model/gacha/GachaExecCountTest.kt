package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GachaExecCountTest {
    @Test
    fun create() {
        val gachaExecCount: GachaExecCount = GachaExecCount.create(1)
        Assertions.assertSame(1, gachaExecCount.count)
    }

    @Test
    fun error() {
        val exception: DomainException = assertThrows<DomainException>() {
            GachaExecCount.create(0)
        }
        Assertions.assertSame("GachaExecCount is invalid.", exception.message)
    }
}
