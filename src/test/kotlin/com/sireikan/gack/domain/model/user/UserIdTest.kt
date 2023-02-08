package com.sireikan.gack.domain.model.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserIdTest {
    @Test
    fun create() {
        val userId: UserId = UserId(1)
        Assertions.assertSame(1, userId.getValue())
    }
}
