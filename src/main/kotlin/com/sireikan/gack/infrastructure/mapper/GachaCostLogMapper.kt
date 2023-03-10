package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaCostLog
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface GachaCostLogMapper {
    @Insert("insert into gacha_cost_log (gacha_id, cost_type, cost, created) values (#{gachaId}, #{costType}, #{cost}, #{created})")
    fun insert(gachaCostLog: GachaCostLog)
}
