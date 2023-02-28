package com.sireikan.gack.application.service.usecase.user.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserGetListOutputDataTest {
    @Test
    fun create() {
        val userGetListOutputData: UserGetListOutputData = UserGetListOutputData.create(emptyList())
        Assertions.assertSame(0, userGetListOutputData.userList.size)
    }
}
