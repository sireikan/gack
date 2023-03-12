package com.sireikan.gack.http.controller

import com.sireikan.gack.infrastructure.mapper.MysqlExtension
import com.sireikan.gack.openapi.generated.model.GachaCostRequest
import com.sireikan.gack.openapi.generated.model.GachaCostResponse
import com.sireikan.gack.openapi.generated.model.GachaInfoRequest
import com.sireikan.gack.openapi.generated.model.GachaInfoResponse
import com.sireikan.gack.openapi.generated.model.GachaPostRequest
import com.sireikan.gack.openapi.generated.model.GachaProbabilityRequest
import com.sireikan.gack.openapi.generated.model.GachaProbabilityResponse
import com.sireikan.gack.openapi.generated.model.GachaPutRequest
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

    @Test
    fun putGacha() {
        webClient.put().uri("/gacha/1")
            .body(
                BodyInserters.fromValue(
                    GachaPutRequest(
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
            .expectStatus().isBadRequest
    }

    @Sql("/sql/GachaControllerTest/putGacha_exist.sql")
    @Test
    fun putGacha_exist() {
        webClient.put().uri("/gacha/1")
            .body(
                BodyInserters.fromValue(
                    GachaPutRequest(
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

    @Test
    fun putGachaGachaIdGachaInfoId() {
        webClient.put().uri("/gacha/1/gachaInfo/")
            .body(
                BodyInserters.fromValue(
                    GachaInfoRequest(
                        "name",
                        "banner",
                        1,
                    ),
                ),
            )
            .exchange()
            .expectStatus().isBadRequest
    }

    @Sql("/sql/GachaControllerTest/putGachaGachaIdGachaInfoId_exist.sql")
    @Test
    fun putGachaGachaIdGachaInfoId_exist() {
        webClient.put().uri("/gacha/1/gachaInfo/")
            .body(
                BodyInserters.fromValue(
                    GachaInfoRequest(
                        "name2",
                        "banner2",
                        2,
                    ),
                ),
            )
            .exchange()
            .expectStatus().isOk

        val expected: GachaResponse = GachaResponse(
            GachaInfoResponse("name2", "banner2", 2),
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
    fun putGachaGachaIdGachaCostId() {
        webClient.put().uri("/gacha/1/gachaCost/")
            .body(
                BodyInserters.fromValue(
                    MultipleGachaCostRequest(
                        listOf(
                            GachaCostRequest(
                                2,
                                2,
                            ),
                        ),
                    ),
                ),
            )
            .exchange()
            .expectStatus().isBadRequest
    }

    @Sql("/sql/GachaControllerTest/putGachaGachaIdGachaCostId_exist.sql")
    @Test
    fun putGachaGachaIdGachaCostId_exist() {
        webClient.put().uri("/gacha/1")
            .body(
                BodyInserters.fromValue(
                    MultipleGachaCostRequest(
                        listOf(
                            GachaCostRequest(
                                2,
                                2,
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
                listOf(GachaCostResponse(2, 2)),
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
    fun putGachaGachaIdGachaProbabilityId() {
        webClient.put().uri("/gacha/1/gachaProbability/")
            .body(
                BodyInserters.fromValue(
                    MultipleGachaProbabilityRequest(
                        listOf(
                            GachaProbabilityRequest(
                                200,
                                2,
                                2L,
                                2,
                            ),
                        ),
                    ),
                ),
            )
            .exchange()
            .expectStatus().isBadRequest
    }

    @Sql("/sql/GachaControllerTest/putGachaGachaIdGachaProbabilityId_exist.sql")
    @Test
    fun putGachaGachaIdGachaProbabilityId_exist() {
        webClient.put().uri("/gacha/1/gachaProbability/")
            .body(
                BodyInserters.fromValue(
                    MultipleGachaProbabilityRequest(
                        listOf(
                            GachaProbabilityRequest(
                                200,
                                2,
                                2L,
                                2,
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
                listOf(GachaProbabilityResponse(200, 2, 2L, 2)),
            ),
        )
        webClient.get().uri("/gacha/1").exchange()
            .expectStatus().isOk
            .expectBody(GachaResponse::class.java)
            .isEqualTo(expected)
    }

    @Test
    fun deleteGacha() {
        webClient.delete().uri("/gacha/1")
            .exchange()
            .expectStatus().isBadRequest
    }

    @Sql("/sql/GachaControllerTest/deleteGacha_exist.sql")
    @Test
    fun deleteGacha_exist() {
        webClient.delete().uri("/gacha/1")
            .exchange()
            .expectStatus().isOk

        webClient.get().uri("/gacha/1").exchange()
            .expectStatus().isNotFound
    }
}
