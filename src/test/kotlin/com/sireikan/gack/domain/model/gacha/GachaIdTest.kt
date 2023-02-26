package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GachaIdTest {
    @Test
    fun create() {
        val gachaId: GachaId = GachaId.create(1)
        Assertions.assertSame(1L, gachaId.id)
    }

    @Test
    fun error() {
        val exception: DomainException = assertThrows<DomainException>() {
            GachaId.create(0)
        }
        Assertions.assertSame("GachaId is invalid.", exception.message)
    }
}
