package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ObjectIdTest {
    @Test
    fun create() {
        val objectId: ObjectId = ObjectId.create(1)
        Assertions.assertSame(1L, objectId.id)
    }

    @Test
    fun error() {
        val exception: DomainException = assertThrows<DomainException>() {
            ObjectId.create(-1)
        }
        Assertions.assertSame("ObjectId is invalid.", exception.message)
    }
}
