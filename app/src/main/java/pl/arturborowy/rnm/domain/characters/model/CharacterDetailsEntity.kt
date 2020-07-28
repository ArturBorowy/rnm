package pl.arturborowy.rnm.domain.characters.model

import java.util.*

data class CharacterDetailsEntity(
    val created: Date,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val currentLocation: CharacterLocationEntity,
    val name: String,
    val originLocation: CharacterLocationEntity,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)