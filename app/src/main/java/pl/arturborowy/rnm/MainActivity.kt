package pl.arturborowy.rnm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import org.koin.android.ext.android.inject
import pl.arturborowy.rnm.base.ui.fragment.BaseFragmentFactory

class MainActivity : AppCompatActivity() {

    private val baseFragmentFactory: BaseFragmentFactory by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupFragmentManager()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigate(actionResId: Int) {
        findNavController(R.id.nav_host_fragment).navigate(actionResId)
    }

    private fun setupFragmentManager() {
        supportFragmentManager.fragmentFactory = baseFragmentFactory
    }
}