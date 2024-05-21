package emmanuelmuturia.carizma.home.uilayer

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import emmanuelmuturia.carizma.R
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.commons.domainlayer.CarizmaState
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBottomNavigationBar
import emmanuelmuturia.carizma.commons.uilayer.state.ErrorScreen
import emmanuelmuturia.carizma.commons.uilayer.state.LoadingScreen
import java.util.Calendar

@Composable
fun HomeScreen(
    navigateToHomeScreen: () -> Unit,
    navigateToSearchScreen: () -> Unit,
    navigateToGarageScreen: () -> Unit,
    navigateToPlayerScreen: (Int) -> Unit,
    homeScreenViewModel: HomeScreenViewModel
) {

    val carizmaState by homeScreenViewModel.carizmaState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        CarizmaBackgroundImage()

        when (carizmaState) {

            is CarizmaState.Error -> ErrorScreen {}

            is CarizmaState.Loading -> LoadingScreen()

            is CarizmaState.Success -> HomeScreenElements(
                navigateToHomeScreen = navigateToHomeScreen,
                navigateToSearchScreen = navigateToSearchScreen,
                navigateToGarageScreen = navigateToGarageScreen,
                navigateToPlayerScreen = navigateToPlayerScreen,
                carizmaState = carizmaState
            )

        }

    }

}

@Composable
fun HomeScreenElements(
    navigateToHomeScreen: () -> Unit,
    navigateToSearchScreen: () -> Unit,
    navigateToGarageScreen: () -> Unit,
    navigateToPlayerScreen: (Int) -> Unit,
    carizmaState: CarizmaState<List<Car>>
) {

    val carList = (carizmaState as CarizmaState.Success<List<Car>>).data

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HomeScreenHeader()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(weight = 1f)
                .padding(bottom = 7.dp)
        ) {

            items(items = carList) { car ->
                HighlightCar(car = car, navigateToPlayerScreen = { navigateToPlayerScreen(car.carId) })
            }

            item {
                Spacer(modifier = Modifier.height(height = 21.dp))
            }

            item {

                Text(
                    modifier = Modifier.padding(start = 14.dp),
                    text = "Formula One (F1)",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(height = 3.5.dp))

                LazyRow {
                    items(items = carList) { car ->
                        CarList(car = car, navigateToPlayerScreen = { navigateToPlayerScreen(car.carId) })
                    }
                }
            }

        }

        CarizmaBottomNavigationBar(
            navigateToHomeScreen = navigateToHomeScreen,
            navigateToSearchScreen = navigateToSearchScreen,
            navigateToGarageScreen = navigateToGarageScreen
        )

    }
    
}


@Composable
private fun HomeScreenHeader() {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 49.dp, start = 14.dp, end = 14.dp, bottom = 21.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = displayGreeting(context = context),
            style = MaterialTheme.typography.headlineLarge
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                modifier = Modifier
                    .padding(end = 21.dp)
                    .size(size = 35.dp)
                    .clickable(onClick = { }),
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications",
                tint = Color.White
            )

            Icon(
                modifier = Modifier
                    .size(size = 35.dp)
                    .clickable(onClick = {

                    }),
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Settings",
                tint = Color.White
            )
        }
    }

}

@Composable
fun HighlightCar(car: Car, navigateToPlayerScreen: (Int) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 42.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(
            modifier = Modifier
                .height(height = 140.dp)
                .width(width = 182.dp)
                .clickable(onClick = { navigateToPlayerScreen(car.carId) }),
            shape = RoundedCornerShape(size = 21.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                AsyncImage(
                    model = car.carImage,
                    contentDescription = "Highlight Car",
                    contentScale = ContentScale.Crop
                )

            }

        }

        Spacer(modifier = Modifier.width(width = 7.dp))

        Text(
            text = car.carName,
            style = MaterialTheme.typography.titleLarge
        )

    }

}

@Composable
fun CarList(
    car: Car,
    navigateToPlayerScreen: (Int) -> Unit
) {

    Column(
        modifier = Modifier.padding(start = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .height(height = 140.dp)
                .width(width = 140.dp)
                .clickable(onClick = { navigateToPlayerScreen(car.carId) }),
            shape = RoundedCornerShape(size = 21.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                AsyncImage(
                    model = car.carImage,
                    contentDescription = "The Latest Car",
                    contentScale = ContentScale.Crop
                )

            }

        }

        Spacer(modifier = Modifier.height(height = 7.dp))

        Text(
            text = car.carName,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(height = 21.dp))

    }

}


private fun displayGreeting(context: Context): String {
    return when (Calendar.getInstance()[Calendar.HOUR_OF_DAY]) {
        in 0..11 -> context.getString(R.string.good_morning)
        in 12..16 -> context.getString(R.string.good_afternoon)
        else -> context.getString(R.string.good_evening)
    }
}