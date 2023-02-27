package com.sireikan.gack.http.model.gacha

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * 
 * @param probability 
 * @param objectType 
 * @param objectId 
 * @param objectCount 
 */
data class GachaProbabilityResponse(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("probability") val probability: kotlin.Any? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("object_type") val objectType: kotlin.Any? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("object_id") val objectId: kotlin.Any? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("object_count") val objectCount: kotlin.Any? = null
) {

}

