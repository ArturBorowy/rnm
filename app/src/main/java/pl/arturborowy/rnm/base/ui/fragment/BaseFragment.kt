package pl.arturborowy.rnm.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import org.koin.android.ext.android.inject
import pl.arturborowy.rnm.BR
import pl.arturborowy.rnm.MainActivity
import pl.arturborowy.rnm.base.ui.addOnPropertyChangedCallback
import pl.arturborowy.rnm.base.ui.viewmodel.DatabindingSubscriber
import pl.arturborowy.rnm.base.ui.viewmodel.FragmentViewModel

abstract class BaseFragment<T : FragmentViewModel>(
    override val viewModel: T,
    private val layoutResId: Int
) : ViewModelAwareFragment(), DatabindingSubscriber {

    override val callbacks
            by inject<MutableCollection<Pair<ObservableField<*>, Observable.OnPropertyChangedCallback>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            layoutResId,
            container,
            false
        )
        binding.setVariable(BR.vm, viewModel)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToDesiredDestinationChanges()
    }

    private fun listenToDesiredDestinationChanges() {
        viewModel.desiredDestination.addOnPropertyChangedCallback {
            postDesiredDestinationToParentActivity(it)
        }.addToCallbacks()
    }

    private fun postDesiredDestinationToParentActivity(desiredDestination: Int?) {
        desiredDestination?.let { (activity as? MainActivity)?.navigate(it) }
    }

    override fun onDestroyView() {
        clearCallbacks()
        super.onDestroyView()
    }

    open fun tagFragment(): String = this::class.java.simpleName

    open fun onBackPressed(): Boolean {
        return viewModel.onBackPressed()
    }
}
