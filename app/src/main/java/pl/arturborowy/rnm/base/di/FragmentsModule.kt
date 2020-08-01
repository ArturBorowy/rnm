package pl.arturborowy.rnm.base.di

import androidx.navigation.fragment.NavHostFragment
import org.koin.core.definition.Definition
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.arturborowy.rnm.base.ui.fragment.BaseFragment
import pl.arturborowy.rnm.character.details.CharacterDetailsFragment
import pl.arturborowy.rnm.character.list.CharacterListFragment
import timber.log.Timber

val fragmentsModule = module {
    single { NavHostFragment() }

    fragment { CharacterListFragment(get(), get()) }
    fragment { CharacterDetailsFragment(get()) }
}

private inline fun <reified FragmentT : BaseFragment<*>> Module.fragment(
    noinline definition: Definition<FragmentT>
) {
    val className = FragmentT::class.qualifiedName

    if (className == null) {
        Timber.w("Fragment name is null. Class: ${FragmentT::class}, definition: $definition")
    } else {
        factory<BaseFragment<*>>(named(className), definition = definition)
    }
}