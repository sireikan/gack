package com.sireikan.gack.application.service.usecase.user.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreateUserDataTest {
    @Test
    fun create() {
        val data: CreateUserData = CreateUserData.create(1L, "name")
        Assertions.assertSame(1L, data.userId)
        Assertions.assertSame("name", data.userName)
    }
}
