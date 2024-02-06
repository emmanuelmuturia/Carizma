package emmanuelmuturia.carizma.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import emmanuelmuturia.carizma.navigation.navgraph.NavGraph
import emmanuelmuturia.carizma.notifications.datalayer.handler.NotificationsHandler
import emmanuelmuturia.carizma.notifications.dependencyinjection.NotificationsHiltModule
import emmanuelmuturia.carizma.theme.CarizmaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(context = Dispatchers.Main).launch {

            val notificationsDatabase =
                NotificationsHiltModule.providesNotificationsDatabase(context = this@MainActivity)

            val notificationDAO =
                NotificationsHiltModule.providesNotificationDao(notificationsDatabase = notificationsDatabase)

            val notificationsRepository =
                NotificationsHiltModule.providesNotificationsRepository(notificationDAO = notificationDAO)

            try {
                NotificationsHandler(notificationRepository = notificationsRepository).handleNotificationIntent(
                    intent = intent
                )
            } catch (e: IOException) {
                Timber.tag(tag = "Notifications Handling Exception")
                    .e(message = "Could not handle notifications due to ${e.printStackTrace()}")
            }
        }

        setContent {
            CarizmaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(navController = rememberNavController())
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        CoroutineScope(context = Dispatchers.Main).launch {

            val notificationsDatabase =
                NotificationsHiltModule.providesNotificationsDatabase(context = this@MainActivity)

            val notificationDAO =
                NotificationsHiltModule.providesNotificationDao(notificationsDatabase = notificationsDatabase)

            val notificationsRepository =
                NotificationsHiltModule.providesNotificationsRepository(notificationDAO = notificationDAO)

            try {
                intent?.let {
                    NotificationsHandler(notificationRepository = notificationsRepository).handleNotificationIntent(
                        intent = it
                    )
                }
            } catch (e: IOException) {
                Timber.tag(tag = "Notifications Handling Exception")
                    .e(message = "Could not handle notifications due to ${e.printStackTrace()}")
            }
        }
    }

}