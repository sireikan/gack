package com.sireikan.gack.application.service.usecase.user.data

import com.sireikan.gack.domain.model.user.User

class UpdateUserData private constructor(
    val userId: Long,
    val userName: String,
) {
    companion object {
        fun create(userId: Long, userName: String): UpdateUserData {
            return UpdateUserData(userId, userName)
        }
        fun create(user: User): UpdateUserData {
            return UpdateUserData(user.userId.userId, user.name.userName)
        }
    }
}
