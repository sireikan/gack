package com.sireikan.gack.http.model.user

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import kotlinx.serialization.Serializable

/**
 *
 * @param id
 * @param name
 * @param email
 * @param password
 */
@Serializable
data class UserResponse(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("id", required = true) val id: Int,

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("name", required = true) val name: String,

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("email", required = true) val email: String,

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("password", required = true) val password: String
) {

}

