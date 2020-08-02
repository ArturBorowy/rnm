package pl.arturborowy.rnm.base.ui.fragment

import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import org.koin.core.KoinComponent
import org.koin.core.error.NoBeanDefFoundException
import org.koin.core.get
import org.koin.core.qualifier.named

class BaseFragmentFactory : FragmentFactory(), KoinComponent {

    override fun instantiate(classLoader: ClassLoader, className: String) =
        try {
            if (className == "androidx.navigation.fragment.NavHostFragment") {
                get<NavHostFragment>()
            } else {
                get<BaseFragment<*>>(qualifier = named(className))
            }
        } catch (exception: NoBeanDefFoundException) {
            throw NoFragmentDefFoundException(className)
        }
}

private class NoFragmentDefFoundException(className: String) :
    Exception("No definition found for '$className' has been found. Check FragmentsModule.kt.")