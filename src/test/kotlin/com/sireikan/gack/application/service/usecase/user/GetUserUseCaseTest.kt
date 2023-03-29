package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
import com.sireikan.gack.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GetUserUseCaseTest {
    private lateinit var userRepository: UserRepository

    private lateinit var getUserUseCase: GetUserUseCase

    @BeforeEach
    fun setUp() {
        userRepository = Mockito.mock(UserRepository::class.java)
        getUserUseCase = GetUserUseCase(userRepository)
    }

    @Test
    fun find() {
        Mockito.`when`(userRepository.find(UserId(1L))).thenReturn(null)
        val actual = getUserUseCase.execute(1L)

        Assertions.assertSame(null, actual)
    }

    @Test
    fun find_exist() {
        Mockito.`when`(userRepository.find(UserId(1L))).thenReturn(
            User(UserId(1L), UserName("name")),
        )
        val actual = getUserUseCase.execute(1L) ?: return

        Assertions.assertSame(1L, actual.userId)
        Assertions.assertSame("name", actual.userName)
    }
}
