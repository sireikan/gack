package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.application.service.usecase.user.data.CreateUserData
import com.sireikan.gack.domain.model.user.UserName
import com.sireikan.gack.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class CreateUserUseCase(private val userRepository: UserRepository) {
    fun execute(createUserData: CreateUserData): Long {
        return userRepository.insert(UserName(createUserData.userName))
    }
}
