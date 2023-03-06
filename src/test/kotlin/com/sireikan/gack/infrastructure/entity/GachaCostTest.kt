package com.sireikan.gack.infrastructure.entity

import com.sireikan.gack.infrastructure.error.RepositoryException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GachaCostTest {
    @Test
    fun create() {
        val gachaCost: GachaCost = GachaCost.create(1L, 1L, 1, 1, "2023-01-01 00:00:00")
        Assertions.assertSame(1L, gachaCost.id)
        Assertions.assertSame(1L, gachaCost.gachaId)
        Assertions.assertSame(1, gachaCost.costType)
        Assertions.assertSame(1, gachaCost.cost)
        Assertions.assertSame("2023-01-01 00:00:00", gachaCost.created)
    }

    @Test
    fun error_id() {
        val exception: RepositoryException = assertThrows<RepositoryException>() {
            GachaCost.create(-1L, 1L, 1, 1, "2023-01-01 00:00:00")
        }
        Assertions.assertSame("GachaCost is invalid.", exception.message)
    }

    @Test
    fun error_gacha_id() {
        val exception: RepositoryException = assertThrows<RepositoryException>() {
            GachaCost.create(1L, -1L, 1, 1, "2023-01-01 00:00:00")
        }
        Assertions.assertSame("GachaCost is invalid.", exception.message)
    }

    @Test
    fun error_cost() {
        val exception: RepositoryException = assertThrows<RepositoryException>() {
            GachaCost.create(1L, 1L, 1, -1, "2023-01-01 00:00:00")
        }
        Assertions.assertSame("GachaCost is invalid.", exception.message)
    }
}
