package pl.arturborowy.rnm.character.list

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.base.rx.setSchedulers
import pl.arturborowy.rnm.base.ui.viewmodel.FragmentViewModel
import pl.arturborowy.rnm.base.ui.viewmodel.RxJavaSubscriber
import pl.arturborowy.rnm.domain.characters.CharactersRemoteRepository
import timber.log.Timber

class CharacterListViewModel(
    private val charactersRemoteRepository: CharactersRemoteRepository,
    override val disposables: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider
) : FragmentViewModel(), RxJavaSubscriber {

    override fun onViewCreated() {
        charactersRemoteRepository.getCharacters()
            .setSchedulers(schedulerProvider)
            .subscribeBy(
                onSuccess = {Timber.e(it.toString())},
                onError = {
                    //TODO
                    Timber.w(it)
                }
            ).addToSubs()
    }

    override fun onDestroyView() {
        clearSubs()
    }
}