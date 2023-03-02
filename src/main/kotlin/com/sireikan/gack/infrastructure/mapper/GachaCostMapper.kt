package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaCost
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface GachaCostMapper {
    @Select("select * from gacha_cost where gacha_id = #{gacha_id} order by #{order}")
    fun findAllByGachaId(@Param("gacha_id") gachaId: Long, @Param("order") order: String): List<GachaCost>

    @Select("select * from gacha_cost order by #{order}")
    fun findAll(@Param("order") order: String): List<GachaCost>

    @Insert("insert into gacha_cost (id, gacha_id, cost_type, cost, created) values (#{id}, #{gachaId}, #{costType}, #{cost}, #{created})")
    fun insert(gachaCost: GachaCost)
}
