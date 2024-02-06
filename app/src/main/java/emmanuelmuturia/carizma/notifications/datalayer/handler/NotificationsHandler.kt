package emmanuelmuturia.carizma.notifications.datalayer.handler

import android.content.Intent
import emmanuelmuturia.carizma.notifications.domainlayer.entity.NotificationEntity
import emmanuelmuturia.carizma.notifications.domainlayer.repository.NotificationsRepository

class NotificationsHandler(private val notificationRepository: NotificationsRepository) {

    suspend fun handleNotificationIntent(
        intent: Intent
    ) {

        val extras = intent.extras
        if (extras != null) {
            val notificationId = extras.getString("notificationId")
            val notificationTitle = extras.getString("notificationTitle")
            val notificationBody = extras.getString("notificationBody")

            if (notificationId != null) {
                saveNotification(
                    notificationId = notificationId,
                    notificationTitle = notificationTitle,
                    notificationBody = notificationBody
                )
            }
        }

    }


    private suspend fun saveNotification(
        notificationId: String,
        notificationTitle: String?,
        notificationBody: String?,
        notificationTimestamp: Long = System.currentTimeMillis()
    ) {

        notificationRepository.addNotification(
            notificationEntity = NotificationEntity(
                notificationId = notificationId,
                notificationTitle = notificationTitle,
                notificationBody = notificationBody,
                notificationTimestamp = notificationTimestamp
            )
        )

    }

}