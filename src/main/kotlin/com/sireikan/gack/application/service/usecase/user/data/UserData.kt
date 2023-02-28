package com.sireikan.gack.application.service.usecase.user.data

import com.sireikan.gack.domain.model.user.User

class UserData private constructor(
    val userId: Int,
    val userName: String,
    val email: String,
    val password: String,
) {
    companion object {
        fun create(userId: Int, userName: String, email: String, password: String): UserData {
            return UserData(userId, userName, email, password)
        }
        fun create(user: User): UserData {
            return UserData(user.id.userId, user.name.userName, user.email.email, user.password.password)
        }
    }
}
