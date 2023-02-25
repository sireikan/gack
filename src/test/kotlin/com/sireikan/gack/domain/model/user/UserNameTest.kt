package com.sireikan.gack.domain.model.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserNameTest {
    @Test
    fun create() {
        val userName: UserName = UserName("name")
        Assertions.assertSame("name", userName.userName)
    }
}
