package emmanuelmuturia.carizma.main.dependencyInjection

import android.app.Application
import com.google.firebase.FirebaseApp
import emmanuelmuturia.carizma.BuildConfig
import emmanuelmuturia.carizma.car.dependencyinjection.carKoinModule
import emmanuelmuturia.carizma.commons.dependencyinjection.firebaseKoinModule
import emmanuelmuturia.carizma.home.dependencyinjection.homeKoinModule
import emmanuelmuturia.carizma.player.dependencyinjection.playerKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class CarizmaApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(tree = Timber.DebugTree())

        FirebaseApp.initializeApp(this)

        startKoin {
            androidContext(androidContext = this@CarizmaApplication)
            modules(modules = listOf(
                carKoinModule,
                firebaseKoinModule,
                homeKoinModule,
                playerKoinModule
            ))
        }

    }

}