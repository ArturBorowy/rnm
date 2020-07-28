package pl.arturborowy.rnm.data.remote.characters.model

import com.google.gson.annotations.SerializedName

data class CharactersListDto(
    @SerializedName("info")
    val pagingInfoDto: PagingInfoDto? = null,
    @SerializedName("results")
    val results: List<CharacterDetailsDto>? = null
)