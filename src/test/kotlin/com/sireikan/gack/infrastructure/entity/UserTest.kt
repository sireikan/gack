package com.sireikan.gack.infrastructure.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun create() {
        val user = User(1, "name", "email", "password")
        Assertions.assertSame(1, user.id)
        Assertions.assertSame("name", user.name)
        Assertions.assertSame("email", user.email)
        Assertions.assertSame("password", user.password)
    }
}
