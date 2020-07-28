package pl.arturborowy.rnm.data.remote.characters.model

import com.google.gson.annotations.SerializedName

data class PagingInfoDto(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("pages")
    val pages: Int? = null,
    @SerializedName("prev")
    val prev: String? = null
)