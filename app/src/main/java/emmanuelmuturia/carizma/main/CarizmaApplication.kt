package emmanuelmuturia.carizma.main

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import io.grpc.android.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class CarizmaApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(tree = Timber.DebugTree())

    }

}