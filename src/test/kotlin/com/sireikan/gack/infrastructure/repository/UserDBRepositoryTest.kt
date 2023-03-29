package com.sireikan.gack.infrastructure.repository

import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
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
    fun find() {
        Mockito.`when`(userMapper.find(1L)).thenReturn(null)
        val actual = userRepository.find(UserId(1L))

        Assertions.assertNull(actual)
    }

    @Test
    fun find_exist() {
        Mockito.`when`(userMapper.find(1L)).thenReturn(
            User.create(1L, "name", "2023-01-01 00:00:00")
        )
        val actual = userRepository.find(UserId(1L)) ?: return

        Assertions.assertSame(1L, actual.id.userId)
        Assertions.assertSame("name", actual.name.userName)
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

    @Test
    fun update() {
        Mockito.doNothing().`when`(userMapper).update(User.create(1L, "name", "2023-01-01 00:00:00"))
        userRepository.update(com.sireikan.gack.domain.model.user.User(UserId(1L), UserName("name2")))
    }

    @Test
    fun delete() {
        Mockito.doNothing().`when`(userMapper).delete(1L)
        userRepository.delete(UserId(1L))
    }
}
