package com.sireikan.gack.application.service.usecase.user.data

import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GetUserOutputDataTest {
    @Test
    fun create() {
        val data: UserData = UserData.create(User(UserId(1), UserName("name"), Email("email"), Password("password")))
        Assertions.assertSame(1, data.userId)
        Assertions.assertSame("name", data.userName)
        Assertions.assertSame("email", data.email)
        Assertions.assertSame("password", data.password)
    }
}
