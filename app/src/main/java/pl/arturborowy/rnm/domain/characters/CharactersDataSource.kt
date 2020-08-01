package pl.arturborowy.rnm.domain.characters

import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.base.rx.setSchedulers
import pl.arturborowy.rnm.base.ui.viewmodel.RxJavaSubscriber
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity
import pl.arturborowy.rnm.domain.characters.model.CharacterListEntity
import timber.log.Timber

class CharactersDataSource(
    private val charactersInteractor: CharactersInteractor,
    private val schedulerProvider: SchedulerProvider,
    override val disposables: CompositeDisposable
) : PageKeyedDataSource<Int, CharacterDetailsEntity>(), RxJavaSubscriber {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterDetailsEntity>
    ) {
        val firstPageIndex = 1

        charactersInteractor.fetchCharacters(firstPageIndex)
            .setSchedulers(schedulerProvider)
            .subscribeBy(
                onSuccess = { onCharactersFetch(it, callback, firstPageIndex) },
                onError = { Timber.e(it) }
            ).addToSubs()
    }

    private fun onCharactersFetch(
        characterList: CharacterListEntity,
        callback: LoadInitialCallback<Int, CharacterDetailsEntity>,
        pageIndex: Int
    ) {
        callback.onResult(
            characterList.characters,
            pageIndex - 1,
            pageIndex + 1
        )
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterDetailsEntity>
    ) {
        charactersInteractor.fetchCharacters(params.key)
            .setSchedulers(schedulerProvider)
            .subscribeBy(
                onSuccess = { onCharactersFetch(it, callback, params.key) },
                onError = Timber::e
            ).addToSubs()
    }

    private fun onCharactersFetch(
        characterList: CharacterListEntity,
        callback: LoadCallback<Int, CharacterDetailsEntity>,
        pageIndex: Int
    ) {
        callback.onResult(characterList.characters, pageIndex + 1)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterDetailsEntity>
    ) {
    }
}