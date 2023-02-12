package com.sireikan.gack.http.model.user

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import kotlinx.serialization.Serializable

/**
 *
 * @param users
 */
@Serializable
data class MultipleUserResponse(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("users", required = true)
    val users: List<UserResponse>,
)
