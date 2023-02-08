package com.sireikan.gack.application.service.user.impl

import com.sireikan.gack.application.service.user.UserApplicationService
import com.sireikan.gack.application.service.user.common.UserData
import com.sireikan.gack.application.service.user.getlist.UserGetListOutputData
import com.sireikan.gack.domain.model.user.User
import com.sireikan.gack.domain.service.UserService
import org.springframework.stereotype.Service

@Service
class UserApplicationServiceImpl(private val userService: UserService) : UserApplicationService {
    override fun getList(): UserGetListOutputData {
        val userList: List<User> = userService.findAll()
        return UserGetListOutputData(userList.stream().map { user -> UserData(user) }.toList())
    }
}
