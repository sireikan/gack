package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.usecase.user.GetUserListUseCase
import com.sireikan.gack.application.service.usecase.user.data.GetUserListOutputData
import com.sireikan.gack.openapi.generated.controller.UserApi
import com.sireikan.gack.openapi.generated.model.MultipleUserResponse
import com.sireikan.gack.openapi.generated.model.UserRequest
import com.sireikan.gack.openapi.generated.model.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController : UserApi {

    @Autowired
    lateinit var getUserListUseCase: GetUserListUseCase

    override fun getUserId(id: Long): ResponseEntity<UserResponse> {
        return super.getUserId(id)
    }

    override fun getUser(): ResponseEntity<MultipleUserResponse> {
        val outputData: GetUserListOutputData = getUserListUseCase.execute()

        return ResponseEntity(
            MultipleUserResponse(
                users = outputData.userList.stream().map { user -> UserResponse(user.userId, user.userName, user.email, user.password) }.toList(),
            ),
            HttpStatus.OK,
        )
    }

    override fun postUser(userRequest: UserRequest?): ResponseEntity<UserResponse> {
        return super.postUser(userRequest)
    }

    override fun putUserId(id: Long, userRequest: UserRequest?): ResponseEntity<Unit> {
        return super.putUserId(id, userRequest)
    }

    override fun deleteUserId(id: Long): ResponseEntity<Unit> {
        return super.deleteUserId(id)
    }
}
