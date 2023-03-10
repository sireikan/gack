package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.GachaInfoLog
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface GachaInfoLogMapper {
    @Insert("insert into gacha_info_log (gacha_id, gacha_name, banner_image, exec_count, created, deleted) values (#{gachaId}, #{gachaName}, #{bannerImage}, #{execCount}, #{created}, #{deleted})")
    fun insert(gachaInfoLog: GachaInfoLog)
}