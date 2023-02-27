package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaCost
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface GachaCostMapper {
    @Select("<script> select * from gacha_cost where gacha_id = #{gacha_id} order by #{order} </script>")
    fun findAllByGachaId(@Param("gacha_id") gachaId: Long, @Param("order") order: String): List<GachaCost>

    @Select("<script> select * from gacha_cost order by #{order} </script>")
    fun findAll(@Param("order") order: String): List<GachaCost>
}
