package com.sireikan.gack.application.service.user.common

import com.sireikan.gack.domain.model.user.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserDataTest {
    @Test
    fun create() {
        val userData: UserData = UserData(User(UserId(1), UserName("name"), Email("email"), Password("password")))
        Assertions.assertSame(1, userData.userId)
        Assertions.assertSame("name", userData.userName)
        Assertions.assertSame("email", userData.email)
        Assertions.assertSame("password", userData.password)
    }
}
