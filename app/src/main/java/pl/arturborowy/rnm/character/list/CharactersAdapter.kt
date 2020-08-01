package pl.arturborowy.rnm.character.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import pl.arturborowy.rnm.R
import pl.arturborowy.rnm.databinding.ItemCharacterBinding
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

internal class CharactersAdapter(
    private val onClickAction: (CharacterDetailsEntity) -> Unit,
    diffCallback: CharacterDiffCallback
) : PagedListAdapter<CharacterDetailsEntity, CharacterViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(createView(parent))

    private fun createView(parent: ViewGroup) =
        DataBindingUtil.inflate<ItemCharacterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_character,
            parent,
            false
        ).root

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(getItem(position), onClickAction)
}

