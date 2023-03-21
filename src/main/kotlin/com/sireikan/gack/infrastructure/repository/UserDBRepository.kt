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
import java.util.concurrent.ThreadLocalRandom

@Component
class UserDBRepository(private val userMapper: UserMapper) : UserRepository {

    override fun find(userId: UserId): User {
        val user = userMapper.find(userId.userId)
        return User(UserId(user.id), UserName(user.name))
    }

    override fun findAll(userOrderKey: UserOrderKey): List<User> {
        val order = buildOrderColumn(userOrderKey)
        val userList: List<com.sireikan.gack.infrastructure.entity.User> = userMapper.findAll(order)
        return userList.stream().map { user ->
            User(UserId(user.id), UserName(user.name))
        }.toList()
    }

    override fun insert(userName: UserName): Long {
        val id: Long = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE)
        val created: String = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().time)
        userMapper.insert(
            com.sireikan.gack.infrastructure.entity.User.create(
                id,
                userName.userName,
                created,
            ),
        )
        return id
    }

    override fun update(user: User) {
        userMapper.update(
            com.sireikan.gack.infrastructure.entity.User.create(
                user.id.userId,
                user.name.userName,
                null,
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
