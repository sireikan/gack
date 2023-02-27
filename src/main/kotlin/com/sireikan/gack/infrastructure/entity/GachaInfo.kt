package com.sireikan.gack.infrastructure.entity

class GachaInfo private constructor(
    val id: Long,
    val gachaId: Long,
    val gachaName: String,
    val bannerImage: String,
    val execCount: Int,
    val created: String,
) {
    companion object {
        fun create(id: Long, gachaId: Long, gachaName: String, bannerImage: String, execCount: Int, created: String): GachaInfo {
            return GachaInfo(id, gachaId, gachaName, bannerImage, execCount, created)
        }
    }
}
