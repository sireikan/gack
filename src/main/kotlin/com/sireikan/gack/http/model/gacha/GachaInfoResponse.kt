package com.sireikan.gack.http.model.gacha

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * 
 * @param gachaName 
 * @param bannerImage 
 * @param execCount 
 */
data class GachaInfoResponse(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("gacha_name") val gachaName: kotlin.Any? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("banner_image") val bannerImage: kotlin.Any? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("exec_count") val execCount: kotlin.Any? = null
) {

}

