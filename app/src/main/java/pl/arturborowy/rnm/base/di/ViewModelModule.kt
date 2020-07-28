package pl.arturborowy.rnm.base.di

import org.koin.dsl.module
import pl.arturborowy.rnm.character.list.CharacterListViewModel

val viewModelModule = module {
    single { CharacterListViewModel() }
}