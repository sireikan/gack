package com.sireikan.gack.module.infrastructure.repository

import com.sireikan.gack.module.infrastructure.entity.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select


@Mapper
interface UserRepository {
    @Select("select * from user")
    fun findAll(): List<User>
}
