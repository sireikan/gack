package com.sireikan.gack.domain.model.user

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EmailTest {
    @Test
    fun create() {
        val email: Email = Email("email")
        Assertions.assertSame("email", email.email)
    }
}
