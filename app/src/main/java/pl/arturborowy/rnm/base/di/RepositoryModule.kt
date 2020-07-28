package pl.arturborowy.rnm.base.di

import org.koin.dsl.module
import pl.arturborowy.rnm.data.remote.characters.CharactersInMemoryCacheRepository
import pl.arturborowy.rnm.data.remote.characters.CharactersRnmRemoteRepository
import pl.arturborowy.rnm.domain.characters.CharactersCacheRepository
import pl.arturborowy.rnm.domain.characters.CharactersRemoteRepository

val repositoryModule = module {
    single<CharactersRemoteRepository> { CharactersRnmRemoteRepository(get(), get()) }
    single<CharactersCacheRepository> { CharactersInMemoryCacheRepository() }
}