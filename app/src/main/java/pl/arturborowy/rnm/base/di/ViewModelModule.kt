package pl.arturborowy.rnm.base.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.arturborowy.rnm.base.rx.RemoteFetchSchedulerProvider
import pl.arturborowy.rnm.base.ui.view.LoadingScreenViewModel
import pl.arturborowy.rnm.character.details.CharacterDetailsViewModel
import pl.arturborowy.rnm.character.list.CharacterListViewModel

val viewModelModule = module {
    single {
        CharacterListViewModel(
            get(),
            get(),
            get(named<RemoteFetchSchedulerProvider>()),
            get(),
            get(),
            get(),
            get()
        )
    }
    single {
        CharacterDetailsViewModel(
            get(),
            get(),
            get(named<RemoteFetchSchedulerProvider>()),
            get()
        )
    }

    factory { LoadingScreenViewModel() }
}