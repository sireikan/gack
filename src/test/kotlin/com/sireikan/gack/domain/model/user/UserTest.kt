package com.sireikan.gack.domain.model.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun create() {
        val user: User = User(UserId(1), UserName("name"))
        Assertions.assertSame(1, user.id.userId)
        Assertions.assertSame("name", user.name.userName)
    }
}
