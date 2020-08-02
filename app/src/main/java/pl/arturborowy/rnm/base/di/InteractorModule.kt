package pl.arturborowy.rnm.base.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.arturborowy.rnm.base.rx.RemoteFetchSchedulerProvider
import pl.arturborowy.rnm.domain.characters.CharactersDataSource
import pl.arturborowy.rnm.domain.characters.CharactersInteractor

val interactorModule = module {
    single { CharactersInteractor(get(), get()) }

    single { CharactersDataSource(get(), get(named<RemoteFetchSchedulerProvider>()), get(), get()) }
}