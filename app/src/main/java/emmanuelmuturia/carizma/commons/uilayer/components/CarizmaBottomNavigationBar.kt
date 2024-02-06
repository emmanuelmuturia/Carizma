package emmanuelmuturia.carizma.commons.uilayer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import emmanuelmuturia.carizma.navigation.routes.Routes

@Composable
fun CarizmaBottomNavigationBar(navController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .height(72.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        IconButton(onClick = { navController.navigate(route = Routes.HomeScreen.route) }) {
            Icon(imageVector = Icons.Rounded.Home, contentDescription = null)
        }

        IconButton(onClick = { navController.navigate(route = Routes.SearchScreen.route) }) {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
        }

        IconButton(onClick = { navController.navigate(route = Routes.GarageScreen.route) }) {
            Icon(imageVector = Icons.Rounded.Build, contentDescription = null)
        }

        IconButton(onClick = { navController.navigate(route = Routes.ProfileScreen.route) }) {
            Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = null)
        }

    }

}