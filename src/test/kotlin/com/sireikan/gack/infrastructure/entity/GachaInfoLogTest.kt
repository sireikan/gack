package com.sireikan.gack.infrastructure.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GachaInfoLogTest {
    @Test
    fun create() {
        val gachaInfoLog: GachaInfoLog = GachaInfoLog.create(1L, "gacha", "banner", 1, "2023-01-01 00:00:00", null)
        Assertions.assertSame(1L, gachaInfoLog.gachaId)
        Assertions.assertTrue(gachaInfoLog.gachaName == "gacha")
        Assertions.assertTrue(gachaInfoLog.bannerImage == "banner")
        Assertions.assertSame(1, gachaInfoLog.execCount)
        Assertions.assertSame("2023-01-01 00:00:00", gachaInfoLog.created)
        Assertions.assertNull(gachaInfoLog.deleted)
    }
}
