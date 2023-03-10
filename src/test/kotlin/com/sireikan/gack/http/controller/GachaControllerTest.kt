package com.sireikan.gack.http.controller

import com.sireikan.gack.infrastructure.mapper.MysqlExtension
import com.sireikan.gack.openapi.generated.model.GachaCostRequest
import com.sireikan.gack.openapi.generated.model.GachaCostResponse
import com.sireikan.gack.openapi.generated.model.GachaInfoRequest
import com.sireikan.gack.openapi.generated.model.GachaInfoResponse
import com.sireikan.gack.openapi.generated.model.GachaPostRequest
import com.sireikan.gack.openapi.generated.model.GachaProbabilityRequest
import com.sireikan.gack.openapi.generated.model.GachaProbabilityResponse
import com.sireikan.gack.openapi.generated.model.GachaResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaCostRequest
import com.sireikan.gack.openapi.generated.model.MultipleGachaCostResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaProbabilityRequest
import com.sireikan.gack.openapi.generated.model.MultipleGachaProbabilityResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaResponse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.reactive.function.BodyInserters
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
    fun getGacha() {
        val expected: MultipleGachaResponse = MultipleGachaResponse(
            listOf(),
        )
        webClient.get().uri("/gacha/").exchange()
            .expectStatus().isOk
            .expectBody(MultipleGachaResponse::class.java)
            .isEqualTo(expected)
    }

    @Sql("/sql/GachaControllerTest/getGacha_exist.sql")
    @Test
    fun getGacha_exist() {
        val expected: MultipleGachaResponse = MultipleGachaResponse(
            listOf(
                GachaResponse(
                    GachaInfoResponse("name", "https://hoge.png", 1),
                    MultipleGachaCostResponse(
                        listOf(GachaCostResponse(1, 1)),
                    ),
                    MultipleGachaProbabilityResponse(
                        listOf(GachaProbabilityResponse(100, 1, 1L, 1)),
                    ),
                ),
            ),
        )
        webClient.get().uri("/gacha/").exchange()
            .expectStatus().isOk
            .expectBody(MultipleGachaResponse::class.java)
            .isEqualTo(expected)
    }

    @Test
    fun getGachaId() {
        webClient.get().uri("/gacha/1").exchange()
            .expectStatus().isNotFound
    }

    @Sql("/sql/GachaControllerTest/getGachaId_exist.sql")
    @Test
    fun getGachaId_exist() {
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

    @Test
    fun postGacha() {
        webClient.post().uri("/gacha/")
            .body(
                BodyInserters.fromValue(
                    GachaPostRequest(
                        1L,
                        GachaInfoRequest(
                            "name",
                            "banner",
                            1,
                        ),
                        MultipleGachaCostRequest(
                            listOf(
                                GachaCostRequest(
                                    1,
                                    1,
                                ),
                            ),
                        ),
                        MultipleGachaProbabilityRequest(
                            listOf(
                                GachaProbabilityRequest(
                                    100,
                                    1,
                                    1L,
                                    1,
                                ),
                            ),
                        ),
                    ),
                ),
            )
            .exchange()
            .expectStatus().isOk

        val expected: GachaResponse = GachaResponse(
            GachaInfoResponse("name", "banner", 1),
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
