package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.usecase.user.data.UserData
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
import com.sireikan.gack.infrastructure.mapper.MysqlExtension
import com.sireikan.gack.openapi.generated.model.MultipleUserResponse
import com.sireikan.gack.openapi.generated.model.UserResponse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ExtendWith(MysqlExtension::class)
@DirtiesContext
@Sql(scripts = ["/sql/Common/delete.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserControllerTest {

    @Autowired
    lateinit var webClient: WebTestClient

    @Test
    fun getUserId() {
        webClient.get().uri("/user/1").exchange()
            .expectStatus().isNotFound
    }

    @Sql("/sql/UserControllerTest/getUserId_exist.sql")
    @Test
    fun getUserId_exist() {
        val expected: UserResponse = UserResponse(1L, "test")
        webClient.get().uri("/user/1").exchange()
            .expectStatus().isOk
            .expectBody(UserResponse::class.java)
            .isEqualTo(expected)
    }

    @Test
    fun getUser() {
        val expected: MultipleUserResponse = MultipleUserResponse(users = listOf<UserResponse>())
        webClient.get().uri("/user").exchange()
            .expectStatus().isOk
            .expectBody(MultipleUserResponse::class.java)
            .isEqualTo(expected)
    }

    @Sql("/sql/UserControllerTest/getUser_exist.sql")
    @Test
    fun getUser_exist() {
        val userDataList: List<UserData> = listOf(UserData.create(User(UserId(1), UserName("test"))))
        val expected: MultipleUserResponse = MultipleUserResponse(users = userDataList.stream().map { user -> UserResponse(user.userId, user.userName) }.toList())
        webClient.get().uri("/user").exchange()
            .expectStatus().isOk
            .expectBody(MultipleUserResponse::class.java)
            .isEqualTo(expected)
    }

    @Test
    fun postUser() {
        webClient.post().uri("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("{\"userId\":\"1\",\"name\":\"test\"}")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.name").isEqualTo("test")
    }

    @Sql("/sql/UserControllerTest/putUserId.sql")
    @Test
    fun putUserId() {
        webClient.put().uri("/user/1")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("{\"name\":\"test2\"}")
            .exchange()
            .expectStatus().isOk
    }

    @Sql("/sql/UserControllerTest/deleteUserId.sql")
    @Test
    fun deleteUserId() {
        webClient.delete().uri("/user/1")
            .exchange()
            .expectStatus().isOk
    }
}
