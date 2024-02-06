package emmanuelmuturia.carizma.notifications.domainlayer.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import emmanuelmuturia.carizma.notifications.domainlayer.entity.NotificationEntity

@Dao
interface NotificationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotification(notificationEntity: NotificationEntity)

    @Query("SELECT * FROM notifications")
    suspend fun getAllNotifications(): List<NotificationEntity>

    @Delete
    suspend fun deleteNotification(notificationEntity: NotificationEntity)

}