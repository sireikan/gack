package com.sireikan.gack.infrastructure.repository

import com.sireikan.gack.domain.model.user.*
import com.sireikan.gack.domain.repository.UserOrderKey
import com.sireikan.gack.domain.repository.UserRepository
import com.sireikan.gack.infrastructure.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class UserDBRepository(private val userMapper: UserMapper) : UserRepository {
    override fun findAll(userOrderKey: UserOrderKey): List<User> {
        val order = buildOrderColumn(userOrderKey)
        val userList: List<com.sireikan.gack.infrastructure.entity.User> = userMapper.findAll(order)
        return userList.stream().map { user -> User(UserId(user.id), UserName(user.name), Email(user.email), Password(user.password)) }.toList()
    }

    private fun buildOrderColumn(userOrderKey: UserOrderKey): String {
        return when (userOrderKey) {
            UserOrderKey.USER_ID -> "id"
        }
    }
}
