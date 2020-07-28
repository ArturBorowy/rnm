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
import pl.arturborowy.rnm.domain.characters.CharactersRemoteRepository
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity
import timber.log.Timber

class CharacterListViewModel(
    private val charactersRemoteRepository: CharactersRemoteRepository,
    override val disposables: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider
) : FragmentViewModel(), RxJavaSubscriber {

    val characters = ObservableArrayList<CharacterDetailsEntity>()
    val charactersBinding =
        ItemBinding.of<CharacterDetailsEntity>(BR.vm, R.layout.item_character)

    override fun onViewCreated() {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        charactersRemoteRepository.getCharacters()
            .setSchedulers(schedulerProvider)
            .subscribeBy(
                onSuccess = {
                    characters.clear()
                    characters.addAll(it.characters)
                },
                onError = { Timber.w(it) }
            ).addToSubs()
    }

    override fun onDestroyView() {
        clearSubs()
    }
}