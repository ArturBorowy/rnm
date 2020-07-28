package pl.arturborowy.rnm.character.details

import pl.arturborowy.rnm.R
import pl.arturborowy.rnm.base.ui.fragment.BaseFragment

class CharacterDetailsFragment(characterDetailsViewModel: CharacterDetailsViewModel) :
    BaseFragment<CharacterDetailsViewModel>(
        characterDetailsViewModel,
        R.layout.fragment_character_details
    )