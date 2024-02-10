package emmanuelmuturia.carizma.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import emmanuelmuturia.carizma.car.uilayer.CarScreen
import emmanuelmuturia.carizma.commons.uilayer.state.ErrorScreen
import emmanuelmuturia.carizma.commons.uilayer.state.LoadingScreen
import emmanuelmuturia.carizma.garage.uilayer.GarageScreen
import emmanuelmuturia.carizma.home.uilayer.HomeScreen
import emmanuelmuturia.carizma.navigation.routes.Routes
import emmanuelmuturia.carizma.notifications.uilayer.NotificationsScreen
import emmanuelmuturia.carizma.player.uilayer.PlayerScreen
import emmanuelmuturia.carizma.profile.uilayer.ProfileScreen
import emmanuelmuturia.carizma.settings.uilayer.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.PlayerScreen.route) {

        composable(route = Routes.ErrorScreen.route) {
            ErrorScreen(navigateBack = { navController.popBackStack() })
        }

        composable(route = Routes.LoadingScreen.route) {
            LoadingScreen()
        }

        composable(route = Routes.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Routes.PlayerScreen.route) {
            PlayerScreen()
        }

        composable(route = Routes.CarScreen.route) {
            CarScreen()
        }

        composable(route = Routes.NotificationsScreen.route) {
            NotificationsScreen(navController = navController)
        }

        composable(route = Routes.SearchScreen.route) {
            //SearchScreen()
        }

        composable(route = Routes.SettingsScreen.route) {
            SettingsScreen()
        }

        composable(route = Routes.GarageScreen.route) {
            GarageScreen()
        }

        composable(route = Routes.ProfileScreen.route) {
            ProfileScreen()
        }

    }

}