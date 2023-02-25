package com.sireikan.gack.infrastructure.repository

import com.sireikan.gack.domain.repository.UserRepository
import com.sireikan.gack.infrastructure.entity.User
import com.sireikan.gack.infrastructure.mapper.UserMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UserDBRepositoryTest {

    private lateinit var userMapper: UserMapper

    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        userMapper = Mockito.mock(UserMapper::class.java)
        userRepository = UserDBRepository(userMapper)
    }

    @Test
    fun findAll() {
        Mockito.`when`(userMapper.findAll()).thenReturn(emptyList())
        val actual = userRepository.findAll()

        Assertions.assertEquals(0, actual.size)
    }

    @Test
    fun findAll_exist() {
        Mockito.`when`(userMapper.findAll()).thenReturn(
            listOf(User(1, "name", "email", "password")),
        )
        val actual = userRepository.findAll()

        Assertions.assertEquals(1, actual.size)
        Assertions.assertSame(1, actual.get(0).id.getValue())
        Assertions.assertSame("name", actual.get(0).name.getValue())
        Assertions.assertSame("email", actual.get(0).email.getValue())
        Assertions.assertSame("password", actual.get(0).password.getValue())
    }
}