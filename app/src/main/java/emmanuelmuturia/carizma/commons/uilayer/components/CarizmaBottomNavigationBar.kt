package emmanuelmuturia.carizma.commons.uilayer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import emmanuelmuturia.carizma.R
import emmanuelmuturia.carizma.navigation.routes.Routes
import emmanuelmuturia.carizma.theme.CarizmaTranslucent

@Composable
fun CarizmaBottomNavigationBar(
    navigateToHomeScreen: () -> Unit,
    navigateToSearchScreen: () -> Unit,
    navigateToGarageScreen: () -> Unit
) {

    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 21.dp))
            .background(color = CarizmaTranslucent)
            .height(70.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = navigateToHomeScreen) {
            Icon(imageVector = Icons.Rounded.Home, contentDescription = null)
        }

        IconButton(onClick = navigateToSearchScreen) {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
        }

        IconButton(onClick = navigateToGarageScreen) {
            Image(painter = painterResource(id = R.drawable.garage), contentDescription = "Garage")
        }

    }

    Spacer(modifier = Modifier.height(height = 21.dp))

}