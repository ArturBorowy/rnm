package pl.arturborowy.rnm.character.details

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.base.rx.setSchedulers
import pl.arturborowy.rnm.base.ui.viewmodel.FragmentViewModel
import pl.arturborowy.rnm.base.ui.viewmodel.RxJavaSubscriber
import pl.arturborowy.rnm.domain.characters.CharactersInteractor
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity
import timber.log.Timber

class CharacterDetailsViewModel(
    private val charactersInteractor: CharactersInteractor,
    override val disposables: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider
) : FragmentViewModel(), RxJavaSubscriber {

    override fun onViewCreated() {
        presentCachedCharacter()
    }

    private fun presentCachedCharacter() {
        charactersInteractor.getCachedCharacter()
            .setSchedulers(schedulerProvider)
            .subscribeBy(
                onSuccess = ::presentCharacter,
                onError = Timber::w
            ).addToSubs()
    }

    private fun presentCharacter(character: CharacterDetailsEntity) {
    }

    override fun onDestroyView() {
        clearSubs()
    }
}