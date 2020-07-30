package pl.arturborowy.rnm.character.details

import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import pl.arturborowy.rnm.base.error.ThrowableHandler
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.base.rx.setSchedulers
import pl.arturborowy.rnm.base.ui.viewmodel.FragmentViewModel
import pl.arturborowy.rnm.base.ui.viewmodel.RxJavaSubscriber
import pl.arturborowy.rnm.domain.characters.CharactersInteractor
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

class CharacterDetailsViewModel(
    private val charactersInteractor: CharactersInteractor,
    override val disposables: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    private val throwableHandler: ThrowableHandler
) : FragmentViewModel(), RxJavaSubscriber {

    val name = ObservableField("")
    val avatarUrl = ObservableField("")
    val gender = ObservableField("")
    val statusOfLife = ObservableField("")
    val currentLocationName = ObservableField("")
    val originLocationName = ObservableField("")

    override fun onViewCreated() {
        presentCachedCharacter()
    }

    private fun presentCachedCharacter() {
        charactersInteractor.getCachedCharacter()
            .setSchedulers(schedulerProvider)
            .subscribeBy(
                onSuccess = ::presentCharacter,
                onError = throwableHandler::handle
            ).addToSubs()
    }

    private fun presentCharacter(character: CharacterDetailsEntity) {
        name.set(character.name)
        avatarUrl.set(character.imageUrl)
        gender.set(character.gender)
        statusOfLife.set(character.statusOfLife)
        currentLocationName.set(character.currentLocation.name)
        originLocationName.set(character.originLocation.name)
    }

    override fun onDestroyView() {
        clearSubs()
    }
}