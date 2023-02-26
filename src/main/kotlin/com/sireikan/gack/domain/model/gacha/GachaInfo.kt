package com.sireikan.gack.domain.model.gacha

class GachaInfo private constructor(
    val gachaName: GachaName,
    val bannerImage: BannerImage,
    val execCount: GachaExecCount,
) {
    companion object {
        fun create(gachaName: GachaName, bannerImage: BannerImage, execCount: GachaExecCount): GachaInfo {
            return GachaInfo(gachaName, bannerImage, execCount)
        }
    }
}
