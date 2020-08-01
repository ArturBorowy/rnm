package pl.arturborowy.rnm.character.list

import androidx.recyclerview.widget.DiffUtil
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterDetailsEntity>() {

    override fun areItemsTheSame(
        oldItem: CharacterDetailsEntity,
        newItem: CharacterDetailsEntity
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CharacterDetailsEntity,
        newItem: CharacterDetailsEntity
    ) = oldItem == newItem
}