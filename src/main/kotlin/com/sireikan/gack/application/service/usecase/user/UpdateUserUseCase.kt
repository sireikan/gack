package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.application.service.usecase.error.InvalidUserUseCaseException
import com.sireikan.gack.application.service.usecase.user.data.UpdateUserData
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.model.user.UserName
import com.sireikan.gack.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class UpdateUserUseCase(private val userRepository: UserRepository) {
    fun execute(updateUserData: UpdateUserData) {
        val user: User = userRepository.find(UserId(updateUserData.userId)) ?: throw InvalidUserUseCaseException("User is invalid.")
        userRepository.update(User(UserId(updateUserData.userId), UserName(updateUserData.userName)))
    }
}
