package com.sireikan.gack.infrastructure.mapper

import com.sireikan.gack.infrastructure.entity.User
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface UserMapper {
    @Select("select * from user where user_id = #{userId}")
    fun find(@Param("userId") userId: Long): User?

    @Select("select * from user order by #{order}")
    fun findAll(@Param("order") order: String): List<User>

    @Insert("insert into user (user_id, name, created) values (#{userId}, #{name}, #{created})")
    fun insert(user: User)

    @Delete("delete from user where user_id = #{userId}")
    fun delete(@Param("userId") userId: Long)
}
