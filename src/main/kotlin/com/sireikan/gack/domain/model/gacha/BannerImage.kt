package com.sireikan.gack.domain.model.gacha

import com.sireikan.gack.domain.error.DomainException

class BannerImage private constructor(
    val url: String,
) {
    companion object {
        fun create(url: String): BannerImage {
            return BannerImage(url)
        }
    }
    init {
        if (url.isBlank()) {
            throw DomainException("BannerImage is invalid.")
        }
    }
}
