package com.sireikan.gack.application.service.usecase.user.data

import com.sireikan.gack.domain.model.user.User

class UserData private constructor(
    val userId: Long,
    val userName: String,
) {
    companion object {
        fun create(userId: Long, userName: String): UserData {
            return UserData(userId, userName)
        }
        fun create(user: User): UserData {
            return UserData(user.userId.userId, user.name.userName)
        }
    }
}
