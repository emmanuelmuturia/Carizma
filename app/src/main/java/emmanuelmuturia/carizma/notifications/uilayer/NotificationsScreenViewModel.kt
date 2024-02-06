package emmanuelmuturia.carizma.notifications.uilayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.carizma.commons.domainlayer.CarizmaState
import emmanuelmuturia.carizma.notifications.domainlayer.entity.NotificationEntity
import emmanuelmuturia.carizma.notifications.domainlayer.repository.NotificationsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NotificationsScreenViewModel @Inject constructor(
    application: Application,
    private val notificationsRepository: NotificationsRepository
) : AndroidViewModel(application = application) {

    private var _notificationsList = MutableStateFlow<List<NotificationEntity>>(value = emptyList())
    val notificationsList: StateFlow<List<NotificationEntity>> = _notificationsList.asStateFlow()

    private var _notificationsState =
        MutableStateFlow<CarizmaState<Any>>(value = CarizmaState.Loading)
    val notificationsState: StateFlow<CarizmaState<Any>> = _notificationsState.asStateFlow()

    private var _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        getAllNotifications()
    }

    fun getAllNotifications() {
        viewModelScope.launch {

            _notificationsState.update { CarizmaState.Loading }

            try {
                _notificationsList.value = notificationsRepository.getAllNotifications()
                _notificationsState.update { CarizmaState.Success(data = _notificationsList.value) }
            } catch (e: Exception) {
                _notificationsState.update { CarizmaState.Error(message = e) }
            }

        }
    }


    fun refreshNotifications() {
        viewModelScope.launch {
            _isLoading.value = true
            notificationsRepository.getAllNotifications()
            delay(timeMillis = 1400L)
            _isLoading.value = false
        }
    }

    fun deleteNotification(notificationEntity: NotificationEntity) {
        viewModelScope.launch {
            try {
                notificationsRepository.deleteNotification(notificationEntity = notificationEntity)
                getAllNotifications()
            } catch (e: Exception) {
                Timber.tag(tag = "Notification Deletion Exception")
                    .e(
                        message = "Could not delete the notification due to: %s",
                        e.printStackTrace()
                    )
            }
        }
    }

}