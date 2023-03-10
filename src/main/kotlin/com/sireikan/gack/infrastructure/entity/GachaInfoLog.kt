package com.sireikan.gack.infrastructure.entity

import com.sireikan.gack.infrastructure.error.RepositoryException

class GachaInfoLog private constructor(
    val gachaId: Long,
    val gachaName: String,
    val bannerImage: String,
    val execCount: Int,
    val created: String,
    val deleted: String?
) {
    companion object {
        fun create(gachaId: Long, gachaName: String, bannerImage: String, execCount: Int, created: String, deleted: String?): GachaInfoLog {
            return GachaInfoLog(gachaId, gachaName, bannerImage, execCount, created, deleted)
        }
    }
}
