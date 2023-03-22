package com.sireikan.gack.application.service.usecase.user.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CreateUserDataTest {
    @Test
    fun create() {
        val data: CreateUserData = CreateUserData.create("name")
        Assertions.assertSame("name", data.userName)
    }
}
