package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.application.service.usecase.user.data.UserData
import com.sireikan.gack.application.service.usecase.user.data.UserGetListOutputData
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class GetListUserUseCase(private val userRepository: UserRepository) {
    fun execute(): UserGetListOutputData {
        val userList: List<User> = userRepository.findAll()
        return UserGetListOutputData(userList.stream().map { user -> UserData(user) }.toList())
    }
}
