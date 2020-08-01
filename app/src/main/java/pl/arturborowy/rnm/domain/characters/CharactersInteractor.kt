package pl.arturborowy.rnm.domain.characters

import io.reactivex.Completable
import io.reactivex.Single
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

class CharactersInteractor(
    private val charactersCacheRepository: CharactersCacheRepository,
    private val charactersRemoteRepository: CharactersRemoteRepository
) {

    fun fetchCharacters(pageIndex: Int) =
        charactersRemoteRepository.getCharacters(pageIndex)

    fun getCachedCharacter(): Single<CharacterDetailsEntity> =
        Single.create { it.onSuccess(charactersCacheRepository.cachedCharacter!!) }

    fun cacheCharacter(character: CharacterDetailsEntity) =
        Completable.create {
            charactersCacheRepository.cachedCharacter = character
            it.onComplete()
        }
}