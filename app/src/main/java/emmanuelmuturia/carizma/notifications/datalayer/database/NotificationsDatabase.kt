package emmanuelmuturia.carizma.notifications.datalayer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import emmanuelmuturia.carizma.notifications.domainlayer.dao.NotificationDAO
import emmanuelmuturia.carizma.notifications.domainlayer.entity.NotificationEntity

@Database(entities = [NotificationEntity::class], version = 1, exportSchema = false)
abstract class NotificationsDatabase : RoomDatabase() { abstract fun notificationDAO(): NotificationDAO }