package pl.arturborowy.rnm.data.remote.characters.mapper

import pl.arturborowy.rnm.data.remote.Mapper
import pl.arturborowy.rnm.data.remote.characters.model.CurrentLocationDto
import pl.arturborowy.rnm.domain.characters.model.CharacterLocationEntity

class CurrentLocationDtoToEntityMapper : Mapper<CurrentLocationDto, CharacterLocationEntity> {

    override fun map(from: CurrentLocationDto): CharacterLocationEntity {
        return CharacterLocationEntity(from.name!!, from.url!!)
    }
}