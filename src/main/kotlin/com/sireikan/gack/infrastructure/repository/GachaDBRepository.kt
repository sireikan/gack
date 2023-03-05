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
import com.sireikan.gack.infrastructure.mapper.GachaCostMapper
import com.sireikan.gack.infrastructure.mapper.GachaInfoMapper
import com.sireikan.gack.infrastructure.mapper.GachaProbabilityMapper
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.concurrent.ThreadLocalRandom

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
        return Gacha.create(
            gachaId,
            com.sireikan.gack.domain.model.gacha.GachaInfo.create(
                GachaName.create(gachaInfo.gachaName),
                BannerImage.create(gachaInfo.bannerImage),
                GachaExecCount.create(gachaInfo.execCount),
            ),
            gachaCostList.stream().map { cost ->
                com.sireikan.gack.domain.model.gacha.GachaCost.create(
                    CostType.from(cost.costType),
                    Cost.create(cost.cost),
                )
            }.toList(),
            gachaProbabilityList.stream().map { probability ->
                com.sireikan.gack.domain.model.gacha.GachaProbability.create(
                    Probability.create(probability.probability),
                    ObjectType.from(probability.objectType),
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
            Gacha.create(
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
                        CostType.from(cost.costType),
                        Cost.create(cost.cost),
                    )
                }.toList(),
                gachaProbabilityList.stream().filter { gachaProbability ->
                    gachaProbability.gachaId == gachaInfo.gachaId
                }.map { probability ->
                    com.sireikan.gack.domain.model.gacha.GachaProbability.create(
                        Probability.create(probability.probability),
                        ObjectType.from(probability.objectType),
                        ObjectId.create(probability.objectId),
                        ObjectCount.create(probability.objectCount),
                    )
                }.toList(),
            )
        }.toList()
    }

    override fun insert(gacha: Gacha) {
        val created: String = SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(Calendar.getInstance().time)
        gachaInfoMapper.insert(
            GachaInfo.create(
                ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE),
                gacha.gachaId.id,
                gacha.gachaInfo.gachaName.name,
                gacha.gachaInfo.bannerImage.url,
                gacha.gachaInfo.execCount.count,
                created,
            ),
        )
        gacha.gachaCostList.stream().forEach { cost ->
            gachaCostMapper.insert(
                GachaCost.create(
                    ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE),
                    gacha.gachaId.id,
                    cost.costType.value,
                    cost.cost.cost,
                    created,
                ),
            )
        }
        gacha.gachaProbabilityList.stream().forEach { probability ->
            gachaProbabilityMapper.insert(
                GachaProbability.create(
                    ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE),
                    gacha.gachaId.id,
                    probability.probability.probability,
                    probability.objectType.value,
                    probability.objectId.id,
                    probability.objectCount.count,
                    created,
                ),
            )
        }
    }

    private fun buildOrderColumn(gachaOrderKey: GachaOrderKey): String {
        return when (gachaOrderKey) {
            GachaOrderKey.GACHA_ID -> "gacha_id"
        }
    }
}
