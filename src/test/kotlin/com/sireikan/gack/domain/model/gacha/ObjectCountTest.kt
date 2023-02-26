package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ObjectCountTest {
    @Test
    fun create() {
        val objectCount: ObjectCount = ObjectCount.create(1)
        Assertions.assertSame(1, objectCount.count)
    }

    @Test
    fun error() {
        val exception: DomainException = assertThrows<DomainException>() {
            ObjectCount.create(-1)
        }
        Assertions.assertSame("ObjectCount is invalid.", exception.message)
    }
}
