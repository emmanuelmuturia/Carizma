package emmanuelmuturia.carizma.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import emmanuelmuturia.carizma.car.uilayer.CarScreen
import emmanuelmuturia.carizma.commons.uilayer.state.ErrorScreen
import emmanuelmuturia.carizma.commons.uilayer.state.LoadingScreen
import emmanuelmuturia.carizma.garage.uilayer.GarageScreen
import emmanuelmuturia.carizma.home.uilayer.HomeScreen
import emmanuelmuturia.carizma.navigation.routes.Routes
import emmanuelmuturia.carizma.player.uilayer.PlayerScreen
import emmanuelmuturia.carizma.profile.uilayer.ProfileScreen
import emmanuelmuturia.carizma.search.uilayer.SearchScreen
import emmanuelmuturia.carizma.settings.uilayer.SettingsScreen

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
            HomeScreen(
                navigateToHomeScreen = { navController.navigate(route = Routes.HomeScreen.route) },
                navigateToSearchScreen = { navController.navigate(route = Routes.SearchScreen.route) },
                navigateToGarageScreen = { navController.navigate(route = Routes.GarageScreen.route) },
                navController = navController
            )
        }

        composable(route = Routes.PlayerScreen.route, arguments = listOf(
            navArgument(name = "carId") {
                type = NavType.IntType
            }
        )) {
            PlayerScreen(navController = navController, carId = navController.currentBackStackEntry?.arguments?.getInt("carId"))
        }

        composable(route = Routes.CarScreen.route, arguments = listOf(
            navArgument(name = "carId") {
                type = NavType.IntType
            }
        )) {
            CarScreen(navigateBack = { navController.popBackStack() }, carId = navController.currentBackStackEntry?.arguments?.getInt("carId"))
        }

        composable(route = Routes.SearchScreen.route) {
            SearchScreen(
                navigateBack = { navController.popBackStack() },
                navigateToCar = {  }
            )
        }

        composable(route = Routes.SettingsScreen.route) {
            SettingsScreen()
        }

        composable(route = Routes.GarageScreen.route) {
            GarageScreen(navigateBack = { navController.popBackStack() })
        }

        composable(route = Routes.ProfileScreen.route) {
            ProfileScreen()
        }

    }

}