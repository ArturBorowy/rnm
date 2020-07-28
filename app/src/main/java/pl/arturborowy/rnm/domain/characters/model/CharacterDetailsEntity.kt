package pl.arturborowy.rnm.domain.characters.model

import java.util.*

data class CharacterDetailsEntity(
    val created: Date,
    val episodes: List<String>,
    val gender: String,
    val id: Int,
    val image_url: String,
    val currentLocation: CharacterLocationEntity,
    val name: String,
    val originLocation: CharacterLocationEntity,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)