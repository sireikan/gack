package com.sireikan.gack.http.controller

import com.sireikan.gack.application.service.usecase.error.UseCaseException
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
import com.sireikan.gack.openapi.generated.model.PutUserRequest
import com.sireikan.gack.openapi.generated.model.UserRequest
import com.sireikan.gack.openapi.generated.model.UserResponse
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
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
        try {
            val userData: UserData = getUserUseCase.execute(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND,)
            return ResponseEntity(
                UserResponse(
                    userData.userId,
                    userData.userName,
                ),
                HttpStatus.OK,
            )
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun getUser(): ResponseEntity<MultipleUserResponse> {
        try {
            val userListData: UserListData = getUserListUseCase.execute()
            return ResponseEntity(
                MultipleUserResponse(
                    userListData.userList.stream().map { user ->
                        UserResponse(user.userId, user.userName)
                    }.toList(),
                ),
                HttpStatus.OK,
            )
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun postUser(userRequest: UserRequest?): ResponseEntity<UserResponse> {
        if (userRequest == null) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        try {
            val createUserData: CreateUserData = CreateUserData.create(userRequest.userId, userRequest.name)
            val id: Long = createUserUseCase.execute(createUserData)
            return ResponseEntity(
                UserResponse(
                    id,
                    createUserData.userName,
                ),
                HttpStatus.OK,
            )
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun putUserId(
        @Parameter(description = "user id", required = true) @PathVariable(value = "id") id: Long,
        @Parameter(description = "") @Valid @RequestBody(required = false) putUserRequest: PutUserRequest?
    ): ResponseEntity<Unit> {
        if (putUserRequest == null) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        }
        try {
            val updateUserData: UpdateUserData = UpdateUserData.create(id, putUserRequest.name)
            updateUserUseCase.execute(updateUserData)
            return ResponseEntity(
                HttpStatus.OK,
            )
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }

    override fun deleteUserId(id: Long): ResponseEntity<Unit> {
        try {
            deleteUserUseCase.execute(id)
            return ResponseEntity(
                HttpStatus.OK,
            )
        } catch (useCaseException: UseCaseException) {
            return ResponseEntity(
                HttpStatus.BAD_REQUEST,
            )
        } catch (exception: Exception) {
            return ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
            )
        }
    }
}
