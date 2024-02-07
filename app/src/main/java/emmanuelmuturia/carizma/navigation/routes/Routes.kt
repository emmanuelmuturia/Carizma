package emmanuelmuturia.carizma.navigation.routes

sealed class Routes(val route: String) {

    data object ErrorScreen: Routes(route = "errorScreen")

    data object LoadingScreen: Routes(route = "loadingScreen")

    data object HomeScreen: Routes(route = "homeScreen")

    data object PlayerScreen: Routes(route = "playerScreen")

    data object CarScreen: Routes(route = "carScreen")

    data object NotificationsScreen: Routes(route = "notificationsScreen")

    data object SearchScreen: Routes(route = "searchScreen")

    data object SettingsScreen: Routes(route = "settingsScreen")

    data object GarageScreen: Routes(route = "garageScreen")

    data object ProfileScreen: Routes(route = "profileScreen")

}