package pl.arturborowy.rnm.data.remote.characters.mapper

import pl.arturborowy.rnm.data.remote.Mapper
import pl.arturborowy.rnm.data.remote.characters.model.OriginLocationDto
import pl.arturborowy.rnm.domain.characters.model.CharacterLocationEntity

class OriginLocationDtoToEntityMapper : Mapper<OriginLocationDto, CharacterLocationEntity> {

    override fun map(from: OriginLocationDto): CharacterLocationEntity {
        return CharacterLocationEntity(from.name!!, from.url!!)
    }
}