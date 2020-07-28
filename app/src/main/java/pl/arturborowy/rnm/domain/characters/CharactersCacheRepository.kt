package pl.arturborowy.rnm.domain.characters

import io.reactivex.Single
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

interface CharactersCacheRepository {

    var requestedCachedCharacterId : Int?

    fun cacheCharacters(characters: List<CharacterDetailsEntity>)

    fun getCharacters() : Single<List<CharacterDetailsEntity>>
}