package pl.arturborowy.rnm.character.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.fragment_character_list.*
import pl.arturborowy.rnm.R
import pl.arturborowy.rnm.base.ui.fragment.BaseFragment

class CharacterListFragment(
    characterListViewModel: CharacterListViewModel,
    private val diffCallback: CharacterDiffCallback
) : BaseFragment<CharacterListViewModel>(characterListViewModel, R.layout.fragment_character_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCharactersList()
    }

    private fun initCharactersList() {
        val charactersAdapter = CharactersAdapter(viewModel::onCharacterClick, diffCallback)
        list.adapter = charactersAdapter

        viewModel.pagingData.observe(requireActivity()) {
            charactersAdapter.submitList(it)
        }
    }
}