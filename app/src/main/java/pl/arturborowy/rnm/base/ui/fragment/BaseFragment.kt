package pl.arturborowy.rnm.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import pl.arturborowy.rnm.BR
import pl.arturborowy.rnm.base.ui.viewmodel.FragmentViewModel

abstract class BaseFragment<T : FragmentViewModel>(
    override val viewModel: T,
    private val layoutResId: Int
) : ViewModelAwareFragment() {

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

    open fun tagFragment(): String = this::class.java.simpleName

    open fun onBackPressed(): Boolean {
        return viewModel.onBackPressed()
    }
}
