package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.user.UserApplicationService
import com.sireikan.gack.application.service.user.getlist.UserGetListOutputData
import com.sireikan.gack.http.model.user.MultipleUserResponse
import com.sireikan.gack.http.model.user.UserResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@RunWith(SpringRunner::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var userApplicationService: UserApplicationService

    @Test
    fun getUser() {
        given(userApplicationService.getList())
            .willReturn(UserGetListOutputData(emptyList()))

        val result = mockMvc.perform(
            get("/user").contentType(MediaType.APPLICATION_JSON))
            .andReturn()


        val expected: MultipleUserResponse = MultipleUserResponse(users = listOf<UserResponse>())
        Assertions.assertEquals(200, result.response.status)
        Assertions.assertEquals(Json.decodeFromString<MultipleUserResponse>(result.response.contentAsString), expected)
    }
}
