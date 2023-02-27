package com.sireikan.gack.http.model.gacha

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * 
 * @param costType 
 * @param cost 
 */
data class GachaCostResponse(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("cost_type") val costType: kotlin.Any? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("cost") val cost: kotlin.Any? = null
) {

}

