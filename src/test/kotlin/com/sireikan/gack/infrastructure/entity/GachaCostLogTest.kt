package com.sireikan.gack.infrastructure.entity

import com.sireikan.gack.infrastructure.error.RepositoryException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GachaCostLogTest {
    @Test
    fun create() {
        val gachaCostLog: GachaCostLog = GachaCostLog.create(1L, 1, 1, "2023-01-01 00:00:00", null)
        Assertions.assertSame(1L, gachaCostLog.gachaId)
        Assertions.assertSame(1, gachaCostLog.costType)
        Assertions.assertSame(1, gachaCostLog.cost)
        Assertions.assertSame("2023-01-01 00:00:00", gachaCostLog.created)
        Assertions.assertNull(gachaCostLog.deleted)
    }

    @Test
    fun error_gacha_id() {
        val exception: RepositoryException = assertThrows<RepositoryException>() {
            GachaCostLog.create(-1L, 1, 1, "2023-01-01 00:00:00", null)
        }
        Assertions.assertSame("GachaCostLog is invalid.", exception.message)
    }

    @Test
    fun error_cost() {
        val exception: RepositoryException = assertThrows<RepositoryException>() {
            GachaCostLog.create(1L, 1, -1, "2023-01-01 00:00:00", null)
        }
        Assertions.assertSame("GachaCostLog is invalid.", exception.message)
    }
}
