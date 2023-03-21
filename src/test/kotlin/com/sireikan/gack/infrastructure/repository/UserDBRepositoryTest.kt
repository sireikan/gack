package com.sireikan.gack.infrastructure.repository

import com.sireikan.gack.domain.repository.UserOrderKey
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
        Mockito.`when`(userMapper.findAll("id")).thenReturn(emptyList())
        val actual = userRepository.findAll(UserOrderKey.USER_ID)

        Assertions.assertEquals(0, actual.size)
    }

    @Test
    fun findAll_exist() {
        Mockito.`when`(userMapper.findAll("id")).thenReturn(
            listOf(User.create(1L, "name", "2023-01-01 00:00:00")),
        )
        val actual = userRepository.findAll(UserOrderKey.USER_ID)

        Assertions.assertEquals(1, actual.size)
        Assertions.assertSame(1L, actual.get(0).id.userId)
        Assertions.assertSame("name", actual.get(0).name.userName)
    }
}
