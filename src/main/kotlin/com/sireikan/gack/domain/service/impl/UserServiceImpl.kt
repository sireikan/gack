package com.sireikan.gack.domain.service.impl

import com.sireikan.gack.domain.model.user.*
import com.sireikan.gack.domain.service.UserService
import com.sireikan.gack.module.infrastructure.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun findAll(): List<User> {
        val userList: List<com.sireikan.gack.module.infrastructure.entity.User> = userRepository.findAll()
        return userList.stream().map { user -> User(UserId(user.id), UserName(user.name), Email(user.email), Password(user.password)) }.toList()
    }
}
