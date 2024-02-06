package emmanuelmuturia.carizma.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import emmanuelmuturia.carizma.commons.uilayer.state.ErrorScreen
import emmanuelmuturia.carizma.commons.uilayer.state.LoadingScreen
import emmanuelmuturia.carizma.navigation.routes.Routes
import emmanuelmuturia.carizma.notifications.uilayer.NotificationsScreen
import emmanuelmuturia.carizma.search.uilayer.SearchScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {

        composable(route = Routes.ErrorScreen.route) {
            ErrorScreen(navigateBack = { navController.popBackStack() })
        }

        composable(route = Routes.LoadingScreen.route) {
            LoadingScreen()
        }

        composable(route = Routes.HomeScreen.route) {

        }

        composable(route = Routes.PlayerScreen.route) {

        }

        composable(route = Routes.CarScreen.route) {

        }

        composable(route = Routes.NotificationsScreen.route) {
            NotificationsScreen(navController = navController)
        }

        composable(route = Routes.SearchScreen.route) {
            //SearchScreen()
        }

        composable(route = Routes.SettingsScreen.route) {

        }

        composable(route = Routes.GarageScreen.route) {

        }

        composable(route = Routes.ProfileScreen.route) {

        }

    }

}