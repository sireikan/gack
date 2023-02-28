package com.sireikan.gack.infrastructure.repository

import com.sireikan.gack.domain.model.gacha.BannerImage
import com.sireikan.gack.domain.model.gacha.Cost
import com.sireikan.gack.domain.model.gacha.CostType
import com.sireikan.gack.domain.model.gacha.Gacha
import com.sireikan.gack.domain.model.gacha.GachaExecCount
import com.sireikan.gack.domain.model.gacha.GachaId
import com.sireikan.gack.domain.model.gacha.GachaName
import com.sireikan.gack.domain.model.gacha.ObjectCount
import com.sireikan.gack.domain.model.gacha.ObjectId
import com.sireikan.gack.domain.model.gacha.ObjectType
import com.sireikan.gack.domain.model.gacha.Probability
import com.sireikan.gack.domain.repository.GachaOrderKey
import com.sireikan.gack.domain.repository.GachaRepository
import com.sireikan.gack.infrastructure.entity.GachaCost
import com.sireikan.gack.infrastructure.entity.GachaInfo
import com.sireikan.gack.infrastructure.entity.GachaProbability
import com.sireikan.gack.infrastructure.error.RepositoryException
import com.sireikan.gack.infrastructure.mapper.GachaCostMapper
import com.sireikan.gack.infrastructure.mapper.GachaInfoMapper
import com.sireikan.gack.infrastructure.mapper.GachaProbabilityMapper
import org.springframework.stereotype.Component

@Component
class GachaDBRepository(
    private val gachaInfoMapper: GachaInfoMapper,
    private val gachaCostMapper: GachaCostMapper,
    private val gachaProbabilityMapper: GachaProbabilityMapper,
) : GachaRepository {
    override fun find(gachaId: GachaId, gachaOrderKey: GachaOrderKey): Gacha? {
        val order = buildOrderColumn(gachaOrderKey)
        val gachaInfo: GachaInfo = gachaInfoMapper.find(gachaId.id) ?: return null
        val gachaCostList: List<GachaCost> = gachaCostMapper.findAllByGachaId(gachaId.id, order)
        val gachaProbabilityList: List<GachaProbability> = gachaProbabilityMapper.findAllByGachaId(gachaId.id, order)
        return Gacha.reconstruct(
            gachaId,
            com.sireikan.gack.domain.model.gacha.GachaInfo.create(
                GachaName.create(gachaInfo.gachaName),
                BannerImage.create(gachaInfo.bannerImage),
                GachaExecCount.create(gachaInfo.execCount),
            ),
            gachaCostList.stream().map { cost ->
                com.sireikan.gack.domain.model.gacha.GachaCost.create(
                    when (cost.costType) {
                        0 -> CostType.NONE
                        1 -> CostType.GAME_COIN
                        2 -> CostType.PURCHASE_COIN
                        else -> throw RepositoryException("GachaCost is invalid.")
                    },
                    Cost.create(cost.cost),
                )
            }.toList(),
            gachaProbabilityList.stream().map { probability ->
                com.sireikan.gack.domain.model.gacha.GachaProbability.create(
                    Probability.create(probability.probability),
                    when (probability.objectType) {
                        0 -> ObjectType.NONE
                        1 -> ObjectType.CHARACTER
                        2 -> ObjectType.ITEM
                        3 -> ObjectType.GAME_COIN
                        else -> throw RepositoryException("GachaProbability is invalid.")
                    },
                    ObjectId.create(probability.objectId),
                    ObjectCount.create(probability.objectCount),
                )
            }.toList(),
        )
    }

    override fun findAll(gachaOrderKey: GachaOrderKey): List<Gacha> {
        val order = buildOrderColumn(gachaOrderKey)
        val gachaInfoList: List<GachaInfo> = gachaInfoMapper.findAll(order)
        val gachaCostList: List<GachaCost> = gachaCostMapper.findAll(order)
        val gachaProbabilityList: List<GachaProbability> = gachaProbabilityMapper.findAll(order)
        return gachaInfoList.stream().map { gachaInfo ->
            Gacha.reconstruct(
                GachaId.create(gachaInfo.gachaId),
                com.sireikan.gack.domain.model.gacha.GachaInfo.create(
                    GachaName.create(gachaInfo.gachaName),
                    BannerImage.create(gachaInfo.bannerImage),
                    GachaExecCount.create(gachaInfo.execCount),
                ),
                gachaCostList.stream().filter { gachaCost ->
                    gachaCost.gachaId == gachaInfo.gachaId
                }.map { cost ->
                    com.sireikan.gack.domain.model.gacha.GachaCost.create(
                        when (cost.costType) {
                            0 -> CostType.NONE
                            1 -> CostType.GAME_COIN
                            2 -> CostType.PURCHASE_COIN
                            else -> throw RepositoryException("GachaCost is invalid.")
                        },
                        Cost.create(cost.cost),
                    )
                }.toList(),
                gachaProbabilityList.stream().filter { gachaProbability ->
                    gachaProbability.gachaId == gachaInfo.gachaId
                }.map { probability ->
                    com.sireikan.gack.domain.model.gacha.GachaProbability.create(
                        Probability.create(probability.probability),
                        when (probability.objectType) {
                            0 -> ObjectType.NONE
                            1 -> ObjectType.CHARACTER
                            2 -> ObjectType.ITEM
                            3 -> ObjectType.GAME_COIN
                            else -> throw RepositoryException("GachaProbability is invalid.")
                        },
                        ObjectId.create(probability.objectId),
                        ObjectCount.create(probability.objectCount),
                    )
                }.toList(),
            )
        }.toList()
    }

    private fun buildOrderColumn(gachaOrderKey: GachaOrderKey): String {
        return when (gachaOrderKey) {
            GachaOrderKey.GACHA_ID -> "gacha_id"
        }
    }
}
