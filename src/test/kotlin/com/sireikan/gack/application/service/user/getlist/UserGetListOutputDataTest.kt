package com.sireikan.gack.application.service.user.getlist

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserGetListOutputDataTest {
    @Test
    fun create() {
        val userGetListOutputData: UserGetListOutputData = UserGetListOutputData(emptyList())
        Assertions.assertSame(0, userGetListOutputData.userList.size)
    }
}
