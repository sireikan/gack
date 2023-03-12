package com.sireikan.gack.application.service.usecase.user.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GetUserListOutputDataTest {
    @Test
    fun create() {
        val data: GetUserListOutputData = GetUserListOutputData.create(emptyList())
        Assertions.assertSame(0, data.userList.size)
    }
}
