package emmanuelmuturia.carizma.navigation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import emmanuelmuturia.carizma.car.uilayer.CarScreen
import emmanuelmuturia.carizma.car.uilayer.CarScreenViewModel
import emmanuelmuturia.carizma.commons.uilayer.state.ErrorScreen
import emmanuelmuturia.carizma.commons.uilayer.state.LoadingScreen
import emmanuelmuturia.carizma.garage.uilayer.GarageScreen
import emmanuelmuturia.carizma.home.uilayer.HomeScreen
import emmanuelmuturia.carizma.home.uilayer.HomeScreenViewModel
import emmanuelmuturia.carizma.navigation.routes.Routes
import emmanuelmuturia.carizma.player.uilayer.PlayerScreen
import emmanuelmuturia.carizma.player.uilayer.PlayerScreenViewModel
import emmanuelmuturia.carizma.profile.uilayer.ProfileScreen
import emmanuelmuturia.carizma.search.uilayer.SearchScreen
import emmanuelmuturia.carizma.settings.uilayer.SettingsScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(navController: NavHostController) {

    val carScreenViewModel: CarScreenViewModel = koinViewModel()

    val homeScreenViewModel: HomeScreenViewModel = koinViewModel()

    val playerScreenViewModel: PlayerScreenViewModel = koinViewModel()

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
                navigateToPlayerScreen = { carId ->
                    navController.navigate(
                        route = "playerScreen/$carId"
                        //route = Routes.PlayerScreen.getRouteWithArgument(carId = carId)
                    )
                },
                homeScreenViewModel = homeScreenViewModel
            )
        }

        composable(route = Routes.PlayerScreen.route, arguments = listOf(
            navArgument(name = "carId") {
                type = NavType.IntType
            }
        )) { navBackStackEntry ->
                PlayerScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToCarScreen = { carId ->
                        navController.navigate(
                            route = "carScreen/$carId"
                            /*route = Routes.CarScreen.getRouteWithArgument(
                                carId = carId
                            )*/
                        )
                    },
                    playerScreenViewModel = playerScreenViewModel,
                    carId = navBackStackEntry.arguments?.getInt("carId")
                )
        }

        composable(route = Routes.CarScreen.route, arguments = listOf(
            navArgument(name = "carId") {
                type = NavType.IntType
            }
        )) { navBackStackEntry ->
            CarScreen(
                navigateBack = { navController.popBackStack() },
                carScreenViewModel = carScreenViewModel,
                carId = navBackStackEntry.arguments?.getInt("carId")
            )
        }

        composable(route = Routes.SearchScreen.route) {
            SearchScreen(
                navigateBack = { navController.popBackStack() },
                navigateToCar = { }
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