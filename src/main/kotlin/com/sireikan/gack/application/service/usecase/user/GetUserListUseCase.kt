package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.application.service.usecase.user.data.GetUserListOutputData
import com.sireikan.gack.application.service.usecase.user.data.GetUserOutputData
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.repository.UserOrderKey
import com.sireikan.gack.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class GetUserListUseCase(private val userRepository: UserRepository) {
    fun execute(): GetUserListOutputData {
        val userList: List<User> = userRepository.findAll(UserOrderKey.USER_ID)
        return GetUserListOutputData.create(userList.stream().map { user -> GetUserOutputData.create(user.id.userId, user.name.userName, user.email.email, user.password.password) }.toList())
    }
}
