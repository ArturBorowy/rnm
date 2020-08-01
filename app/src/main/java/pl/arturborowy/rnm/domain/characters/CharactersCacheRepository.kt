package pl.arturborowy.rnm.domain.characters

import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

interface CharactersCacheRepository {

    var cachedCharacter: CharacterDetailsEntity?
}