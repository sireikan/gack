package com.sireikan.gack.infrastructure.entity

import com.sireikan.gack.infrastructure.error.RepositoryException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserTest {
    @Test
    fun create() {
        val user = User.create(1L, "name", "2023-01-01 00:00:00")
        Assertions.assertSame(1L, user.id)
        Assertions.assertSame("name", user.name)
        Assertions.assertSame("2023-01-01 00:00:00", user.created)
    }

    @Test
    fun error() {
        val exception: RepositoryException = assertThrows<RepositoryException>() {
            User.create(1L, "", "2023-01-01 00:00:00")
        }
        Assertions.assertSame("User is invalid.", exception.message)
    }
}
