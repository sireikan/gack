package com.sireikan.gack.domain.model.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun create() {
        val user: User = User(UserId(1), UserName("name"), Email("email"), Password("password"))
        Assertions.assertSame(1, user.id.userId)
        Assertions.assertSame("name", user.name.userName)
        Assertions.assertSame("email", user.email.email)
        Assertions.assertSame("password", user.password.password)
    }
}
