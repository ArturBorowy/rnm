package pl.arturborowy.rnm.data.remote.characters.mapper

import pl.arturborowy.rnm.data.remote.Mapper
import pl.arturborowy.rnm.data.remote.characters.model.PagingInfoDto
import pl.arturborowy.rnm.domain.characters.model.PagingInfoEntity

class PagingInfoDtoToEntityMapper : Mapper<PagingInfoDto, PagingInfoEntity> {

    override fun map(from: PagingInfoDto): PagingInfoEntity {
        return PagingInfoEntity(from.count!!, from.next, from.pages!!, from.prev)
    }
}