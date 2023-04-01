package com.sireikan.gack.infrastructure.repository

import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
import com.sireikan.gack.domain.repository.UserOrderKey
import com.sireikan.gack.domain.repository.UserRepository
import com.sireikan.gack.infrastructure.mapper.UserMapper
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class UserDBRepository(private val userMapper: UserMapper) : UserRepository {

    override fun find(userId: UserId): User? {
        val user = userMapper.find(userId.userId) ?: return null
        return User(UserId(user.userId), UserName(user.name))
    }

    override fun findAll(userOrderKey: UserOrderKey): List<User> {
        val order = buildOrderColumn(userOrderKey)
        val userList: List<com.sireikan.gack.infrastructure.entity.User> = userMapper.findAll(order)
        return userList.stream().map { user ->
            User(UserId(user.userId), UserName(user.name))
        }.toList()
    }

    override fun insert(userId: UserId, userName: UserName): Long {
        val created: String = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().time)
        userMapper.insert(
            com.sireikan.gack.infrastructure.entity.User.create(
                null,
                userId.userId,
                userName.userName,
                created,
            ),
        )
        return userId.userId
    }

    override fun update(user: User) {
        userMapper.delete(user.userId.userId)

        val created: String = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().time)
        userMapper.insert(
            com.sireikan.gack.infrastructure.entity.User.create(
                null,
                user.userId.userId,
                user.name.userName,
                created,
            ),
        )
    }

    override fun delete(userId: UserId) {
        userMapper.delete(userId.userId)
    }

    private fun buildOrderColumn(userOrderKey: UserOrderKey): String {
        return when (userOrderKey) {
            UserOrderKey.USER_ID -> "id"
        }
    }
}
