package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.user.common.UserData
import com.sireikan.gack.domain.model.user.*
import com.sireikan.gack.http.model.user.MultipleUserResponse
import com.sireikan.gack.http.model.user.UserResponse
import com.sireikan.gack.module.infrastructure.repository.MysqlExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ExtendWith(MysqlExtension::class)
@DirtiesContext
class UserControllerTest {

    @Autowired
    lateinit var webClient: WebTestClient

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
    fun getUser_exist(@Autowired webClient: WebTestClient) {
        val userDataList: List<UserData> = listOf(
            UserData(User(UserId(1), UserName("test"), Email("test@example.com"), Password("test")))
        )
        val expected: MultipleUserResponse = MultipleUserResponse(users = userDataList.stream().map { user -> UserResponse(user.userId, user.userName, user.email, user.password) }.toList())
        webClient.get().uri("/user").exchange()
            .expectStatus().isOk
            .expectBody(MultipleUserResponse::class.java)
            .isEqualTo(expected)
    }
}
