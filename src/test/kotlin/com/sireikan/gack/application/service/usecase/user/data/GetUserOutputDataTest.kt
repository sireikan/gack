package com.sireikan.gack.application.service.usecase.user.data

import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GetUserOutputDataTest {
    @Test
    fun create() {
        val data: UserData = UserData.create(User(UserId(1L), UserName("name")))
        Assertions.assertSame(1L, data.userId)
        Assertions.assertSame("name", data.userName)
    }
}
