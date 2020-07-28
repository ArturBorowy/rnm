package pl.arturborowy.rnm.base.di

import org.koin.dsl.module
import pl.arturborowy.rnm.base.ui.fragment.BaseFragmentFactory

val utilModule = module {
    single { ClassLoader.getSystemClassLoader() }
    single { BaseFragmentFactory(get()) }
}