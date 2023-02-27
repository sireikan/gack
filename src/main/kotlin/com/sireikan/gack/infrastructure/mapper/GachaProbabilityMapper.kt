package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaProbability
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface GachaProbabilityMapper {
    @Select("<script> select * from gacha_probability where gacha_id = #{gacha_id} order by #{order} </script>")
    fun findAllByGachaId(@Param("gacha_id") gachaId: Long, @Param("order") order: String): List<GachaProbability>

    @Select("<script> select * from gacha_probability order by #{order} </script>")
    fun findAll(@Param("order") order: String): List<GachaProbability>
}
