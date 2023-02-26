package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BannerImageTest {
    @Test
    fun create() {
        val bannerImage: BannerImage = BannerImage.create("https://hogehoge.png")
        Assertions.assertSame("https://hogehoge.png", bannerImage.url)
    }

    @Test
    fun error() {
        val exception: DomainException = assertThrows<DomainException>() {
            BannerImage.create("")
        }
        Assertions.assertSame("BannerImage is invalid.", exception.message)
    }
}
