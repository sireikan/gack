package com.sireikan.gack.application.service.user.impl

import com.sireikan.gack.application.service.user.UserApplicationService
import com.sireikan.gack.domain.model.user.Email
import com.sireikan.gack.domain.model.user.Password
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
import com.sireikan.gack.domain.service.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class UserApplicationServiceImplTest {

    private lateinit var userService: UserService

    private lateinit var userApplicationService: UserApplicationService

    @BeforeEach
    fun setUp() {
        userService = Mockito.mock(UserService::class.java)
        userApplicationService = UserApplicationServiceImpl(userService)
    }

    @Test
    fun findAll() {
        Mockito.`when`(userService.findAll()).thenReturn(emptyList())
        val actual = userApplicationService.getList()

        Assertions.assertEquals(0, actual.userList.size)
    }

    @Test
    fun findAll_exist() {
        Mockito.`when`(userService.findAll()).thenReturn(
            listOf(User(UserId(1), UserName("name"), Email("email"), Password("password"))),
        )
        val actual = userApplicationService.getList()

        Assertions.assertEquals(1, actual.userList.size)
        Assertions.assertSame(1, actual.userList[0].userId)
        Assertions.assertSame("name", actual.userList[0].userName)
        Assertions.assertSame("email", actual.userList[0].email)
        Assertions.assertSame("password", actual.userList[0].password)
    }
}
