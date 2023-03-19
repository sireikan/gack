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
    @Select("select * from user where id = #{id}")
    fun find(@Param("id") id: Long): User

    @Select("select * from user order by #{order}")
    fun findAll(@Param("order") order: String): List<User>

    @Insert("insert into user (id, name, created) values (#{id}, #{name}, #{created})")
    fun insert(user: User)

    @Update("update user set name = #{name} where id = #{id}")
    fun update(user: User)

    @Delete("delete from user where id = #{id}")
    fun delete(@Param("id") id: Long)
}
