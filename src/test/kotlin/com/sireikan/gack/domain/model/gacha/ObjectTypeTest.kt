package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ObjectTypeTest {
    @Test
    fun create() {
        val objectType: ObjectType = ObjectType.from(0)
        Assertions.assertSame(0, objectType.value)
    }

    @Test
    fun error() {
        val exception: DomainException = assertThrows<DomainException>() {
            ObjectType.from(-1)
        }
        Assertions.assertSame("ObjectType is invalid.", exception.message)
    }
}
