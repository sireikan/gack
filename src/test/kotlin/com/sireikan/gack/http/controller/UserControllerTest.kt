package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.user.UserApplicationService
import com.sireikan.gack.application.service.user.common.UserData
import com.sireikan.gack.application.service.user.getlist.UserGetListOutputData
import com.sireikan.gack.domain.model.user.*
import com.sireikan.gack.http.model.user.MultipleUserResponse
import com.sireikan.gack.http.model.user.UserResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.mockito.BDDMockito.given


@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var userApplicationService: UserApplicationService

    @Test
    fun getUser() {
        given(userApplicationService.getList())
            .willReturn(UserGetListOutputData(emptyList()));
        val result = mvc
            .perform(
                get("/user")
            ).andReturn()

        val expected: MultipleUserResponse = MultipleUserResponse(users = listOf<UserResponse>())
        Assertions.assertEquals(200, result.response.status)
        Assertions.assertEquals(Json.decodeFromString<MultipleUserResponse>(result.response.contentAsString), expected)
    }

    @Test
    fun getUser_exist() {
        val userDataList: List<UserData> = listOf(
            UserData(User(UserId(1), UserName("name"), Email("email"), Password("password")))
        )
        given(userApplicationService.getList())
            .willReturn(UserGetListOutputData(
                userDataList
            ));
        val result = mvc
            .perform(
                get("/user")
            ).andReturn()

        val expected: MultipleUserResponse = MultipleUserResponse(users = userDataList.stream().map { user -> UserResponse(user.userId, user.userName, user.email, user.password) }.toList())
        Assertions.assertEquals(200, result.response.status)
        Assertions.assertEquals(Json.decodeFromString<MultipleUserResponse>(result.response.contentAsString), expected)
    }
}
