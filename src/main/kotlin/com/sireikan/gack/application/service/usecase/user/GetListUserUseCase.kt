package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.application.service.usecase.user.data.UserData
import com.sireikan.gack.application.service.usecase.user.data.UserGetListOutputData
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.repository.UserOrderKey
import com.sireikan.gack.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class GetListUserUseCase(private val userRepository: UserRepository) {
    fun execute(): UserGetListOutputData {
        val userList: List<User> = userRepository.findAll(UserOrderKey.USER_ID)
        return UserGetListOutputData.create(userList.stream().map { user -> UserData.create(user.id.userId, user.name.userName, user.email.email, user.password.password) }.toList())
    }
}
