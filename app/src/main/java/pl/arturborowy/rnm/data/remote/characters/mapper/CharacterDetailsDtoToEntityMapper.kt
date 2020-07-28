package pl.arturborowy.rnm.data.remote.characters.mapper

import pl.arturborowy.rnm.base.date.StringToDateConverter
import pl.arturborowy.rnm.data.remote.Mapper
import pl.arturborowy.rnm.data.remote.characters.model.CharacterDetailsDto
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

class CharacterDetailsDtoToEntityMapper(
    private val stringToDateConverter: StringToDateConverter,
    private val originLocationDtoToEntityMapper: OriginLocationDtoToEntityMapper,
    private val currentLocationDtoToEntityMapper: CurrentLocationDtoToEntityMapper
): Mapper<CharacterDetailsDto, CharacterDetailsEntity> {

    override fun map(from: CharacterDetailsDto): CharacterDetailsEntity {
        return CharacterDetailsEntity(
            stringToDateConverter.convert(from.created!!),
            from.episode ?: listOf(),
            from.gender!!,
            from.id!!,
            from.image!!,
            currentLocationDtoToEntityMapper.map(from.currentLocationDto!!),
            from.name!!,
            originLocationDtoToEntityMapper.map(from.originLocationDto!!),
            from.species!!,
            from.status!!,
            from.type!!,
            from.url!!
        )
    }
}