package com.sireikan.gack.infrastructure.entity

import com.sireikan.gack.domain.error.DomainException
import com.sireikan.gack.infrastructure.error.RepositoryException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GachaInfoTest {
    @Test
    fun create() {
        val gachaInfo: GachaInfo = GachaInfo.create(1L, 1L, "name", "https://hogehoge.png", 1, "2023-01-01 00:00:00")
        Assertions.assertSame(1L, gachaInfo.id)
        Assertions.assertSame(1L, gachaInfo.gachaId)
        Assertions.assertSame("name", gachaInfo.gachaName)
        Assertions.assertSame("https://hogehoge.png", gachaInfo.bannerImage)
        Assertions.assertSame(1, gachaInfo.execCount)
        Assertions.assertSame("2023-01-01 00:00:00", gachaInfo.created)
    }

    @Test
    fun error_id() {
        val exception: RepositoryException = assertThrows<RepositoryException>() {
            GachaInfo.create(-1L, 1L, "name", "https://hogehoge.png", 1, "2023-01-01 00:00:00")
        }
        Assertions.assertSame("GachaInfo is invalid.", exception.message)
    }

    @Test
    fun error_gacha_id() {
        val exception: RepositoryException = assertThrows<RepositoryException>() {
            GachaInfo.create(1L, -1L, "name", "https://hogehoge.png", 1, "2023-01-01 00:00:00")
        }
        Assertions.assertSame("GachaInfo is invalid.", exception.message)
    }
}
