package pl.arturborowy.rnm.data.remote.characters.mapper

import pl.arturborowy.rnm.data.remote.Mapper
import pl.arturborowy.rnm.data.remote.characters.model.CharactersListDto
import pl.arturborowy.rnm.domain.characters.model.CharacterListEntity

class CharacterListDtoToEntityMapper(
    private val pagingInfoDtoToEntityMapper: PagingInfoDtoToEntityMapper,
    private val characterDetailsDtoToEntityMapper: CharacterDetailsDtoToEntityMapper
) : Mapper<CharactersListDto, CharacterListEntity> {

    override fun map(from: CharactersListDto): CharacterListEntity {
        return CharacterListEntity(
            pagingInfoDtoToEntityMapper.map(from.pagingInfoDto!!),
            from.results?.map { characterDetailsDtoToEntityMapper.map(it) } ?: listOf()
        )
    }
}