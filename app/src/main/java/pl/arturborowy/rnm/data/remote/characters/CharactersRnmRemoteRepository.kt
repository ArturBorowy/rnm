package pl.arturborowy.rnm.data.remote.characters

import pl.arturborowy.rnm.data.remote.RnmService
import pl.arturborowy.rnm.data.remote.characters.mapper.CharacterListDtoToEntityMapper
import pl.arturborowy.rnm.domain.characters.CharactersRemoteRepository

class CharactersRnmRemoteRepository(
    private val rnmService: RnmService,
    private val characterListDtoToEntityMapper: CharacterListDtoToEntityMapper
) : CharactersRemoteRepository {

    override fun getCharacters() =
        rnmService.getCharacters()
            .map { characterListDtoToEntityMapper.map(it) }
}