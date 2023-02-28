package com.sireikan.gack.application.service.usecase.gacha.data

class GachaInfoData private constructor(
    val gachaName: String,
    val bannerImage: String,
    val execCount: Int,
) {
    companion object {
        fun create(gachaName: String, bannerImage: String, execCount: Int): GachaInfoData {
            return GachaInfoData(gachaName, bannerImage, execCount)
        }
    }
}
