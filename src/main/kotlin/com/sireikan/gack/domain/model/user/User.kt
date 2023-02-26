package com.sireikan.gack.domain.model.user

data class User(
    val id: UserId,
    val name: UserName,
    val email: Email,
    val password: Password,
)
