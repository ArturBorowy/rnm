package pl.arturborowy.rnm.data.remote.characters

import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import pl.arturborowy.rnm.domain.characters.CharactersCacheRepository
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

class CharactersInMemoryCacheRepository : CharactersCacheRepository {

    private val charactersSubject =
        BehaviorSubject.create<List<CharacterDetailsEntity>>()

    override fun cacheCharacters(characters: List<CharacterDetailsEntity>) {
        charactersSubject.onNext(characters)
    }

    override fun getCharacters(): Single<List<CharacterDetailsEntity>> =
        charactersSubject.firstOrError()
}