package com.sireikan.gack.http.controller

import com.sireikan.gack.infrastructure.mapper.MysqlExtension
import com.sireikan.gack.openapi.generated.model.GachaCostResponse
import com.sireikan.gack.openapi.generated.model.GachaInfoResponse
import com.sireikan.gack.openapi.generated.model.GachaProbabilityResponse
import com.sireikan.gack.openapi.generated.model.GachaResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaCostResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaProbabilityResponse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ExtendWith(MysqlExtension::class)
@DirtiesContext
@Sql(scripts = ["/sql/Common/delete.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class GachaControllerTest {
    @Autowired
    lateinit var webClient: WebTestClient

    @Test
    fun getGachaId() {
        webClient.get().uri("/gacha/1").exchange()
            .expectStatus().isNotFound
    }

    @Sql("/sql/GachaControllerTest/getGachaId_exist.sql")
    @Test
    fun getGachaId_exist(@Autowired webClient: WebTestClient) {
        val expected: GachaResponse = GachaResponse(
            GachaInfoResponse("name", "https://hoge.png", 1),
            MultipleGachaCostResponse(
                listOf(GachaCostResponse(1, 1)),
            ),
            MultipleGachaProbabilityResponse(
                listOf(GachaProbabilityResponse(100, 1, 1L, 1)),
            ),
        )
        webClient.get().uri("/gacha/1").exchange()
            .expectStatus().isOk
            .expectBody(GachaResponse::class.java)
            .isEqualTo(expected)
    }
}
