package pl.arturborowy.rnm.character.list

import androidx.paging.LivePagedListBuilder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import pl.arturborowy.rnm.R
import pl.arturborowy.rnm.base.error.ThrowableHandler
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.base.rx.setSchedulers
import pl.arturborowy.rnm.base.ui.view.LoadingScreenViewModel
import pl.arturborowy.rnm.base.ui.viewmodel.FragmentViewModel
import pl.arturborowy.rnm.base.ui.viewmodel.RxJavaSubscriber
import pl.arturborowy.rnm.domain.characters.CharactersDataSource
import pl.arturborowy.rnm.domain.characters.CharactersInteractor
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity
import pl.arturborowy.rnm.domain.characters.model.CharactersDataSourceFactory

class CharacterListViewModel(
    private val charactersInteractor: CharactersInteractor,
    override val disposables: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    val loadingScreenViewModel: LoadingScreenViewModel,
    private val throwableHandler: ThrowableHandler,
    private val charactersDataSourceFactory: CharactersDataSourceFactory,
    private val charactersDataSource: CharactersDataSource
) : FragmentViewModel(), RxJavaSubscriber {

    companion object {
        private const val PAGE_SIZE = 20
    }

    val pagingData =
        LivePagedListBuilder<Int, CharacterDetailsEntity>(charactersDataSourceFactory, PAGE_SIZE)
            .build()

    override fun onViewCreated() {
        super.onViewCreated()
        charactersDataSource.setLoadingScreenViewModel(loadingScreenViewModel)
    }

    fun onCharacterClick(character: CharacterDetailsEntity) {
        charactersInteractor.cacheCharacter(character)
            .setSchedulers(schedulerProvider)
            .subscribeBy(
                onComplete = { navigate(R.id.action_characterList_to_characterDetails) },
                onError = throwableHandler::handle
            ).addToSubs()
    }

    override fun onDestroyView() {
        clearSubs()
    }
}