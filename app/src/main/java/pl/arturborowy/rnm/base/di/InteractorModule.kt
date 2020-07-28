package pl.arturborowy.rnm.base.di

import org.koin.dsl.module
import pl.arturborowy.rnm.domain.characters.CharactersInteractor

val interactorModule = module {
    single { CharactersInteractor(get(), get()) }
}