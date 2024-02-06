package emmanuelmuturia.carizma.notifications.uilayer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import emmanuelmuturia.carizma.commons.domainlayer.CarizmaState
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader
import emmanuelmuturia.carizma.commons.uilayer.state.ErrorScreen
import emmanuelmuturia.carizma.commons.uilayer.state.LoadingScreen
import emmanuelmuturia.carizma.notifications.domainlayer.entity.NotificationEntity
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NotificationsScreen(navigateBack: () -> Unit) {

    val notificationsScreenViewModel: NotificationsScreenViewModel = hiltViewModel()

    val notificationsList by notificationsScreenViewModel.notificationsList.collectAsStateWithLifecycle()

    val notificationsState by notificationsScreenViewModel.notificationsState.collectAsStateWithLifecycle()

    when (notificationsState) {

        is CarizmaState.Error -> ErrorScreen(navigateBack = navigateBack)
        is CarizmaState.Loading -> LoadingScreen()
        else -> Box(modifier = Modifier.fillMaxSize()) {

            CarizmaBackgroundImage()

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                CarizmaHeader(
                    navigateBack = navigateBack,
                    headerTitle = "Notifications"
                )

                if (notificationsList.isNotEmpty()) {
                    NotificationScreenContent()
                } else {
                    EmptyNotificationsScreen()
                }


            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationScreenContent() {

    val notificationsScreenViewModel: NotificationsScreenViewModel = hiltViewModel()

    val notificationsList by notificationsScreenViewModel.notificationsList.collectAsStateWithLifecycle()

    val isLoading by notificationsScreenViewModel.isLoading.collectAsStateWithLifecycle()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = notificationsScreenViewModel::refreshNotifications,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            )
        }) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(space = 7.dp)
        ) {
            items(notificationsList, key = { notificationEntity ->
                notificationEntity.notificationId
            }) { notificationEntity ->

                val swipeToDismissState = rememberDismissState()

                DisposableEffect(swipeToDismissState) {
                    onDispose {
                        if (swipeToDismissState.isDismissed(direction = DismissDirection.EndToStart)) {
                            notificationsScreenViewModel.deleteNotification(notificationEntity = notificationEntity)
                        }
                    }
                }

                AnimatedVisibility(
                    visible = !swipeToDismissState.isDismissed(direction = DismissDirection.EndToStart),
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    SwipeToDismiss(
                        modifier = Modifier.fillMaxWidth(),
                        state = swipeToDismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        background = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Transparent),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        },
                        dismissContent = {
                            NotificationsCard(notificationEntity = notificationEntity)
                        },
                    )
                }
            }
        }

    }

}


@Composable
private fun NotificationsCard(notificationEntity: NotificationEntity) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp),
        shape = RoundedCornerShape(size = 21.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(width = 3.dp, color = Color.White)
    ) {

        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = notificationEntity.notificationTitle!!,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notificationEntity.notificationBody!!,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Received on ${
                    SimpleDateFormat(
                        "MMM dd, yyyy hh:mm a",
                        Locale.getDefault()
                    ).format(notificationEntity.notificationTimestamp)
                }",
                style = MaterialTheme.typography.labelLarge
            )
        }

    }

}


@Composable
private fun EmptyNotificationsScreen() {

    val notificationsScreenViewModel: NotificationsScreenViewModel = hiltViewModel()

    val isLoading by notificationsScreenViewModel.isLoading.collectAsStateWithLifecycle()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)


    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = notificationsScreenViewModel::refreshNotifications,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            )
        }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            item {
                Text(
                    text = "You have no notifications",
                    style = MaterialTheme.typography.titleLarge
                )
            }

        }
    }

}