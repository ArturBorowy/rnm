package pl.arturborowy.rnm.base.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.arturborowy.rnm.base.ui.viewmodel.ActivityViewModel

abstract class ViewModelAwareActivity : AppCompatActivity() {

    protected abstract val viewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}
