package emmanuelmuturia.carizma.notifications.domainlayer.repository

import emmanuelmuturia.carizma.notifications.domainlayer.entity.NotificationEntity

interface NotificationsRepository {

    suspend fun addNotification(notificationEntity: NotificationEntity)

    suspend fun getAllNotifications(): List<NotificationEntity>

    suspend fun deleteNotification(notificationEntity: NotificationEntity)

}