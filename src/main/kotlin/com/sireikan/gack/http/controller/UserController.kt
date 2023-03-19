package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.usecase.user.CreateUserUseCase
import com.sireikan.gack.application.service.usecase.user.DeleteUserUseCase
import com.sireikan.gack.application.service.usecase.user.GetUserListUseCase
import com.sireikan.gack.application.service.usecase.user.GetUserUseCase
import com.sireikan.gack.application.service.usecase.user.UpdateUserUseCase
import com.sireikan.gack.application.service.usecase.user.data.CreateUserData
import com.sireikan.gack.application.service.usecase.user.data.UpdateUserData
import com.sireikan.gack.application.service.usecase.user.data.UserData
import com.sireikan.gack.application.service.usecase.user.data.UserListData
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
    lateinit var getUserUseCase: GetUserUseCase
    @Autowired
    lateinit var getUserListUseCase: GetUserListUseCase
    @Autowired
    lateinit var createUserUseCase: CreateUserUseCase
    @Autowired
    lateinit var updateUserUseCase: UpdateUserUseCase
    @Autowired
    lateinit var deleteUserUseCase: DeleteUserUseCase

    override fun getUserId(id: Long): ResponseEntity<UserResponse> {
        val userData: UserData = getUserUseCase.execute(id)
        return ResponseEntity(
            UserResponse(
                userData.userId,
                userData.userName
            ),
            HttpStatus.OK
        )
    }

    override fun getUser(): ResponseEntity<MultipleUserResponse> {
        val userListData: UserListData = getUserListUseCase.execute()

        return ResponseEntity(
            MultipleUserResponse(
                userListData.userList.stream().map { user ->
                    UserResponse(user.userId, user.userName)
                }.toList(),
            ),
            HttpStatus.OK,
        )
    }

    override fun postUser(userRequest: UserRequest?): ResponseEntity<UserResponse> {
        if (userRequest == null) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        val createUserData: CreateUserData = CreateUserData.create(userRequest.name)
        val id: Long = createUserUseCase.execute(createUserData)
        return ResponseEntity(
            UserResponse(
                id,
                createUserData.userName
            ),
            HttpStatus.OK
        )
    }

    override fun putUserId(id: Long, userRequest: UserRequest?): ResponseEntity<Unit> {
        if (userRequest == null) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        val updateUserData: UpdateUserData = UpdateUserData.create(id, userRequest.name)
        updateUserUseCase.execute(updateUserData)
        return ResponseEntity(
            HttpStatus.OK
        )
    }

    override fun deleteUserId(id: Long): ResponseEntity<Unit> {
        deleteUserUseCase.execute(id)
        return ResponseEntity(
            HttpStatus.OK
        )
    }
}
