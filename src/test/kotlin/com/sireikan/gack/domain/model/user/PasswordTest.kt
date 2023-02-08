package com.sireikan.gack.domain.model.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PasswordTest {
    @Test
    fun create() {
        val password: Password = Password("password")
        Assertions.assertSame("password", password.getValue())
    }
}
