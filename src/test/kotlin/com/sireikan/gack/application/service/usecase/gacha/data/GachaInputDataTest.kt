package com.sireikan.gack.application.service.usecase.gacha.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaInputDataTest {
    @Test
    fun create() {
        val gachaInputData: GachaInputData = GachaInputData.create(1L)
        Assertions.assertSame(1L, gachaInputData.gachaId)
    }
}
