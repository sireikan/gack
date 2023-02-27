package com.sireikan.gack.http.model.gacha

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * 
 * @param infos 
 */
data class MultipleGachaInfoResponse(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("infos") val infos: kotlin.Any? = null
) {

}

