package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.application.service.usecase.user.data.UserData
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class GetUserUseCase(private val userRepository: UserRepository) {
    fun execute(id: Long): UserData {
        val user: User = userRepository.find(UserId(id))
        return UserData.Companion.create(user.id.userId, user.name.userName)
    }
}
