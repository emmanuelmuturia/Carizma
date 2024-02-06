package emmanuelmuturia.carizma.notifications.datalayer.repository

import emmanuelmuturia.carizma.notifications.domainlayer.dao.NotificationDAO
import emmanuelmuturia.carizma.notifications.domainlayer.entity.NotificationEntity
import emmanuelmuturia.carizma.notifications.domainlayer.repository.NotificationsRepository
import timber.log.Timber
import javax.inject.Inject

class NotificationsRepositoryImplementation @Inject constructor(
    private val notificationDAO: NotificationDAO
) : NotificationsRepository {

    override suspend fun addNotification(notificationEntity: NotificationEntity) {
        try {
            notificationDAO.addNotification(notificationEntity = notificationEntity)
        } catch (e: Exception) {
            Timber.tag(tag = "Notification Addition Exception")
                .e(message = "Could not add the notification due to: %s", e.printStackTrace())
        }
    }

    override suspend fun getAllNotifications(): List<NotificationEntity> {
        return try {
            notificationDAO.getAllNotifications().reversed()
        } catch (e: Exception) {
            Timber.tag(tag = "Notification Retrieval Exception")
                .e(message = "Could not retrieve the notifications due to: %s", e.printStackTrace())
            return emptyList()
        }
    }

    override suspend fun deleteNotification(notificationEntity: NotificationEntity) {
        try {
            notificationDAO.deleteNotification(notificationEntity = notificationEntity)
        } catch (e: Exception) {
            Timber.tag(tag = "Notification Deletion Exception")
                .e(message = "Could not delete the notification due to: %s", e.printStackTrace())
        }
    }

}