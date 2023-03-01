package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaProbability
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface GachaProbabilityMapper {
    @Select("select * from gacha_probability where gacha_id = #{gacha_id} order by #{order}")
    fun findAllByGachaId(@Param("gacha_id") gachaId: Long, @Param("order") order: String): List<GachaProbability>

    @Select("select * from gacha_probability order by #{order}")
    fun findAll(@Param("order") order: String): List<GachaProbability>

    @Insert("insert into gacha_probability (id, gacha_id, probability, object_type, object_id, object_count, created) values (#{id}, #{gachaId}, #{probability}, #{objectType}, #{objectId}, #{objectCount}, #{created})")
    fun insert(gachaProbability: GachaProbability)
}
