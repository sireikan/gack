package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaInfo
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface GachaInfoMapper {
    @Select("<script> select * from gacha_info where gacha_id = #{gacha_id} </script>")
    fun find(@Param("gacha_id") gachaId: Long): GachaInfo

    @Select("<script> select * from gacha_info order by #{order} </script>")
    fun findAll(@Param("order") order: String): List<GachaInfo>
}
