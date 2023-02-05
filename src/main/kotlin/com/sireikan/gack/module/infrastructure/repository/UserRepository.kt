package com.sireikan.gack.module.infrastructure.repository

import com.sireikan.gack.module.infrastructure.entity.User
import org.apache.ibatis.annotations.Mapper


@Mapper
interface UserRepository {
    fun findTodoList(): List<User>
}
