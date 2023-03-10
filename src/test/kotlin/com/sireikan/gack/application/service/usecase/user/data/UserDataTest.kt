package com.sireikan.gack.application.service.usecase.user.data

import com.sireikan.gack.domain.model.user.Email
import com.sireikan.gack.domain.model.user.Password
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserDataTest {
    @Test
    fun create() {
        val userData: UserData = UserData.create(User(UserId(1), UserName("name"), Email("email"), Password("password")))
        Assertions.assertSame(1, userData.userId)
        Assertions.assertSame("name", userData.userName)
        Assertions.assertSame("email", userData.email)
        Assertions.assertSame("password", userData.password)
    }
}
