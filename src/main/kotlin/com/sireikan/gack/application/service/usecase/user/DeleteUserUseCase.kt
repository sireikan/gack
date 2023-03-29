package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.domain.model.user.UserId
import com.sireikan.gack.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class DeleteUserUseCase(private val userRepository: UserRepository) {
    fun execute(id: Long) {
        userRepository.delete(UserId(id))
    }
}
