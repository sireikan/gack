package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.domain.model.user.Email
import com.sireikan.gack.domain.model.user.Password
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
import com.sireikan.gack.domain.repository.UserOrderKey
import com.sireikan.gack.domain.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class GetListUserUseCaseTest {

    private lateinit var userRepository: UserRepository

    private lateinit var getListUserUseCase: GetListUserUseCase

    @BeforeEach
    fun setUp() {
        userRepository = Mockito.mock(UserRepository::class.java)
        getListUserUseCase = GetListUserUseCase(userRepository)
    }

    @Test
    fun findAll() {
        Mockito.`when`(userRepository.findAll(UserOrderKey.USER_ID)).thenReturn(emptyList())
        val actual = getListUserUseCase.execute()

        Assertions.assertEquals(0, actual.userList.size)
    }

    @Test
    fun findAll_exist() {
        Mockito.`when`(userRepository.findAll(UserOrderKey.USER_ID)).thenReturn(
            listOf(User(UserId(1), UserName("name"), Email("email"), Password("password"))),
        )
        val actual = getListUserUseCase.execute()

        Assertions.assertEquals(1, actual.userList.size)
        Assertions.assertSame(1, actual.userList[0].userId)
        Assertions.assertSame("name", actual.userList[0].userName)
        Assertions.assertSame("email", actual.userList[0].email)
        Assertions.assertSame("password", actual.userList[0].password)
    }
}
