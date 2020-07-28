package pl.arturborowy.rnm.data.remote.characters.model

import com.google.gson.annotations.SerializedName

data class CurrentLocationDto(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)