package pl.arturborowy.rnm.domain.characters

import io.reactivex.Single
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

class CharactersInteractor(
    private val charactersCacheRepository: CharactersCacheRepository,
    private val charactersRemoteRepository: CharactersRemoteRepository
) {

    fun fetchCharacters() =
        charactersRemoteRepository.getCharacters()
            .doOnSuccess { charactersCacheRepository.cacheCharacters(it.characters) }

    fun getCachedCharacter(id: Int): Single<CharacterDetailsEntity> =
        charactersCacheRepository.getCharacters()
            .map { characters ->
                characters.firstOrNull { character -> character.id == id }
            }
}