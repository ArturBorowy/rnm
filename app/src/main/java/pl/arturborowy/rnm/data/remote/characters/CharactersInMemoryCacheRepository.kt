package pl.arturborowy.rnm.data.remote.characters

import pl.arturborowy.rnm.domain.characters.CharactersCacheRepository
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

class CharactersInMemoryCacheRepository : CharactersCacheRepository {

    override var cachedCharacter: CharacterDetailsEntity? = null
}