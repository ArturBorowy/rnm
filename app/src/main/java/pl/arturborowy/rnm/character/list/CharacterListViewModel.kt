package pl.arturborowy.rnm.character.list

import androidx.databinding.ObservableArrayList
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import me.tatarka.bindingcollectionadapter2.ItemBinding
import pl.arturborowy.rnm.BR
import pl.arturborowy.rnm.R
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.base.rx.setSchedulers
import pl.arturborowy.rnm.base.ui.viewmodel.FragmentViewModel
import pl.arturborowy.rnm.base.ui.viewmodel.RxJavaSubscriber
import pl.arturborowy.rnm.domain.characters.CharactersInteractor
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity
import timber.log.Timber

class CharacterListViewModel(
    private val charactersInteractor: CharactersInteractor,
    override val disposables: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider
) : FragmentViewModel(), RxJavaSubscriber {

    val characters = ObservableArrayList<CharacterDetailsEntity>()
    val charactersBinding =
        ItemBinding.of<CharacterDetailsEntity>(BR.vm, R.layout.item_character)
            .apply { bindExtra(BR.onClick, ::onCharacterClick) }

    override fun onViewCreated() {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        charactersInteractor.fetchCharacters()
            .setSchedulers(schedulerProvider)
            .subscribeBy(
                onSuccess = {
                    characters.clear()
                    characters.addAll(it.characters)
                },
                onError = { Timber.w(it) }
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