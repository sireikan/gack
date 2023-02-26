package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GachaNameTest {
    @Test
    fun create() {
        val gachaName: GachaName = GachaName.create("name")
        Assertions.assertSame("name", gachaName.name)
    }

    @Test
    fun error_blank() {
        val exception: DomainException = assertThrows<DomainException>() {
            GachaName.create("")
        }
        Assertions.assertSame("GachaName is invalid.", exception.message)
    }

    @Test
    fun error_long() {
        val exception: DomainException = assertThrows<DomainException>() {
            GachaName.create("012345678901234567890")
        }
        Assertions.assertSame("GachaName is longer.", exception.message)
    }
}
