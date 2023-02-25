package com.sireikan.gack.domain.repository

import com.sireikan.gack.domain.model.user.User

interface UserRepository {
    fun findAll(userOrderKey: UserOrderKey): List<User>
}
