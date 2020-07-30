package pl.arturborowy.rnm.character.list

import androidx.databinding.ObservableArrayList
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import pl.arturborowy.rnm.BR
import pl.arturborowy.rnm.R
import pl.arturborowy.rnm.base.error.ThrowableHandler
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.base.rx.setSchedulers
import pl.arturborowy.rnm.base.ui.ItemBindingWrapper
import pl.arturborowy.rnm.base.ui.view.LoadingScreenViewModel
import pl.arturborowy.rnm.base.ui.viewmodel.FragmentViewModel
import pl.arturborowy.rnm.base.ui.viewmodel.RxJavaSubscriber
import pl.arturborowy.rnm.domain.characters.CharactersInteractor
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

class CharacterListViewModel(
    private val charactersInteractor: CharactersInteractor,
    override val disposables: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    itemBindingWrapper: ItemBindingWrapper,
    val loadingScreenViewModel: LoadingScreenViewModel,
    private val throwableHandler: ThrowableHandler
) : FragmentViewModel(), RxJavaSubscriber {

    val characters = ObservableArrayList<CharacterDetailsEntity>()
    val charactersBinding =
        itemBindingWrapper.createBinding<CharacterDetailsEntity>(
            BR.vm,
            R.layout.item_character,
            mapOf(BR.onClick to ::onCharacterClick)
        )

    override fun onViewCreated() {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        loadingScreenViewModel.show()
        characters.clear()

        charactersInteractor.fetchCharacters()
            .setSchedulers(schedulerProvider)
            .doAfterTerminate(loadingScreenViewModel::hide)
            .subscribeBy(
                onSuccess = { characters.addAll(it.characters) },
                onError = throwableHandler::handle
            ).addToSubs()
    }

    private fun onCharacterClick(character: CharacterDetailsEntity) {
        charactersInteractor.requestCachedCharacterId(character.id)
        navigate(R.id.action_characterList_to_characterDetails)
    }

    override fun onDestroyView() {
        clearSubs()
    }
}