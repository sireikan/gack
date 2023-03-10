package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaInfo
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface GachaInfoMapper {
    @Select("select * from gacha_info where gacha_id = #{gacha_id}")
    fun find(@Param("gacha_id") gachaId: Long): GachaInfo?

    @Select("select * from gacha_info order by #{order}")
    fun findAll(@Param("order") order: String): List<GachaInfo>

    @Insert("insert into gacha_info (id, gacha_id, gacha_name, banner_image, exec_count, created) values (#{id}, #{gachaId}, #{gachaName}, #{bannerImage}, #{execCount}, #{created})")
    fun insert(gachaInfo: GachaInfo)

    @Delete("delete from gacha_info where gacha_id = #{gachaId}")
    fun deleteByGachaId(@Param("gacha_id") gachaId: Long)
}
