package pl.arturborowy.rnm.domain.characters.model

data class PagingInfoEntity(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)