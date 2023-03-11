package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaProbabilityLog
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface GachaProbabilityLogMapper {
    @Insert("insert into gacha_probability_log (gacha_id, probability, object_type, object_id, object_count, created, deleted) values (#{gachaId}, #{probability}, #{objectType}, #{objectId}, #{objectCount}, #{created}, #{deleted})")
    fun insert(gachaProbabilityLog: GachaProbabilityLog)
}
