package com.sireikan.gack.infrastructure.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun create() {
        val user = User.create(1, "name", "2023-01-01 00:00:00")
        Assertions.assertSame(1, user.id)
        Assertions.assertSame("name", user.name)
        Assertions.assertSame("2023-01-01 00:00:00", user.created)
    }
}
