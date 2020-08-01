package pl.arturborowy.rnm.domain.characters

import io.reactivex.Single
import pl.arturborowy.rnm.domain.characters.model.CharacterListEntity

interface CharactersRemoteRepository {

    fun getCharacters(pageIndex: Int): Single<CharacterListEntity>
}