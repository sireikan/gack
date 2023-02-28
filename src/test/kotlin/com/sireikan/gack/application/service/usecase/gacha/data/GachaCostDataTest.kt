package com.sireikan.gack.application.service.usecase.gacha.data

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaCostDataTest {
    @Test
    fun create() {
        val gachaCostData: GachaCostData = GachaCostData.create(1, 1)
        Assertions.assertSame(1, gachaCostData.costType)
        Assertions.assertSame(1, gachaCostData.cost)
    }
}
