package pl.arturborowy.rnm.character.list

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pl.arturborowy.rnm.databinding.ItemCharacterBinding
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(character: CharacterDetailsEntity?, onClickAction: (CharacterDetailsEntity) -> Unit) {
        character?.let {
            val binding = DataBindingUtil.bind<ItemCharacterBinding>(itemView)
            binding?.vm = character
            binding?.onClick = { onClickAction(character) }
        }
    }
}