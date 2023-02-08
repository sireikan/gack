package com.sireikan.gack.domain.service

import com.sireikan.gack.domain.model.user.User

interface UserService {
    fun findAll(): List<User>
}
