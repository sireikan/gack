package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {
    @Select("<script> select * from user order by #{order} </script>")
    fun findAll(@Param("order") order: String): List<User>
}
