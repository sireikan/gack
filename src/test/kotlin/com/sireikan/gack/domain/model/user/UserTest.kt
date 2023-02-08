package com.sireikan.gack.domain.model.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun create() {
        val user: User = User(UserId(1), UserName("name"), Email("email"), Password("password"))
        Assertions.assertSame(1, user.id.getValue())
        Assertions.assertSame("name", user.name.getValue())
        Assertions.assertSame("email", user.email.getValue())
        Assertions.assertSame("password", user.password.getValue())
    }
}
