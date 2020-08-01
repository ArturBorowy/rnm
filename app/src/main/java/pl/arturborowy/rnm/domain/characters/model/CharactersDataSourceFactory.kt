package pl.arturborowy.rnm.domain.characters.model

import androidx.paging.DataSource
import pl.arturborowy.rnm.domain.characters.CharactersDataSource

class CharactersDataSourceFactory(private val charactersDataSource: CharactersDataSource) :
    DataSource.Factory<Int, CharacterDetailsEntity>() {

    override fun create(): DataSource<Int, CharacterDetailsEntity> = charactersDataSource
}