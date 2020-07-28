package pl.arturborowy.rnm.domain.characters.model

data class CharacterListEntity(
    val pagingInfo: PagingInfoEntity,
    val characters: List<CharacterDetailsEntity>
)