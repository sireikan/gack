package com.sireikan.gack.domain.repository

import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName

interface UserRepository {
    fun find(userId: UserId): User?
    fun findAll(userOrderKey: UserOrderKey): List<User>
    fun insert(userId: UserId, userName: UserName): Long
    fun update(user: User)
    fun delete(userId: UserId)
}
