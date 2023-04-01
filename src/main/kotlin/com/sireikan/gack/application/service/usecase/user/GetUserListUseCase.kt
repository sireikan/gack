package com.sireikan.gack.application.service.usecase.user

import com.sireikan.gack.application.service.usecase.user.data.UserData
import com.sireikan.gack.application.service.usecase.user.data.UserListData
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.repository.UserOrderKey
import com.sireikan.gack.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class GetUserListUseCase(private val userRepository: UserRepository) {
    fun execute(): UserListData {
        val userList: List<User> = userRepository.findAll(UserOrderKey.USER_ID)
        return UserListData.create(
            userList.stream().map { user ->
                UserData.create(user.userId.userId, user.name.userName)
            }.toList(),
        )
    }
}
