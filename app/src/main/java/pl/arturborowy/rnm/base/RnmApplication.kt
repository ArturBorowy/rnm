package pl.arturborowy.rnm.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pl.arturborowy.rnm.BuildConfig
import pl.arturborowy.rnm.base.di.definitionModule
import timber.log.Timber

class RnmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@RnmApplication)
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            }
            modules(definitionModule)
        }
    }
}