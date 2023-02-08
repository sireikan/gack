package com.sireikan.gack.domain.service.impl

import com.sireikan.gack.domain.service.UserService
import com.sireikan.gack.module.infrastructure.entity.User
import com.sireikan.gack.module.infrastructure.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class UserServiceImplTest {

    private lateinit var userRepository: UserRepository

    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userRepository = mock(UserRepository::class.java)
        userService = UserServiceImpl(userRepository)
    }

    @Test
    fun findAll() {
        Mockito.`when`(userRepository.findAll()).thenReturn(emptyList())
        val actual = userService.findAll()

        Assertions.assertEquals(0, actual.size)
    }

    @Test
    fun findAll_exist() {
        Mockito.`when`(userRepository.findAll()).thenReturn(listOf(
            User(1, "name", "email", "password")
        ))
        val actual = userService.findAll()

        Assertions.assertEquals(1, actual.size)
        Assertions.assertSame(1, actual.get(0).id.getValue())
        Assertions.assertSame("name", actual.get(0).name.getValue())
        Assertions.assertSame("email", actual.get(0).email.getValue())
        Assertions.assertSame("password", actual.get(0).password.getValue())
    }
}
