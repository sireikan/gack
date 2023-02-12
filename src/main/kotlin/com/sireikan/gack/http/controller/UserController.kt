package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.user.UserApplicationService
import com.sireikan.gack.application.service.user.getlist.UserGetListOutputData
import com.sireikan.gack.http.model.user.MultipleUserResponse
import com.sireikan.gack.http.model.user.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController : UserApi {

    @Autowired
    lateinit var userApplicationService: UserApplicationService

    override fun getUser(): ResponseEntity<MultipleUserResponse> {
        val outputData: UserGetListOutputData = userApplicationService.getList()

        return ResponseEntity(
            MultipleUserResponse(
                users = outputData.userList.stream().map { user -> UserResponse(user.userId, user.userName, user.email, user.password) }.toList(),
            ),
            HttpStatus.OK,
        )
    }
}
