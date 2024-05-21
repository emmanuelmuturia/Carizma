package emmanuelmuturia.carizma.car.uilayer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.commons.domainlayer.CarizmaState
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader
import emmanuelmuturia.carizma.commons.uilayer.state.ErrorScreen
import emmanuelmuturia.carizma.commons.uilayer.state.LoadingScreen
import emmanuelmuturia.carizma.commons.uilayer.theme.CarizmaOrange

@Composable
fun CarScreen(
    navigateBack: () -> Unit,
    carScreenViewModel: CarScreenViewModel,
    carId: Int?
) {

    LaunchedEffect(key1 = carId) {
        if (carId != null) {
            carScreenViewModel.getCarById(carId = carId)
        }
    }

    val car by carScreenViewModel.carizmaCar.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

        CarScreenElements(
            car = car,
            navigateBack = navigateBack,
            carScreenViewModel = carScreenViewModel
        )

    }

}

@Composable
private fun CarScreenElements(car: Car?, navigateBack: () -> Unit, carScreenViewModel: CarScreenViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        car?.let { CarizmaHeader(navigateBack = navigateBack, headerTitle = it.carName) }

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {
                car?.let { CarDetails(car = it, carScreenViewModel = carScreenViewModel) }
            }

        }

    }
}

@Composable
private fun CarDetails(car: Car, carScreenViewModel: CarScreenViewModel) {

    val carDescription by carScreenViewModel.carDescription.collectAsStateWithLifecycle()

    val isResponseLoading by carScreenViewModel.isResponseLoading.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .height(height = 280.dp)
                .width(width = 280.dp)
                .clickable {

                }, shape = RoundedCornerShape(size = 21.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                AsyncImage(
                    model = car.carImage,
                    contentDescription = "Player Car",
                    contentScale = ContentScale.Crop
                )

            }

        }

        Spacer(modifier = Modifier.height(height = 28.dp))

        Text(
            text = car.carName,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(height = 28.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 7.dp)
        ) {

            Text(text = buildAnnotatedString {

                withStyle(style = SpanStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)) {
                    append(text = "Top Speed: ")
                }

                withStyle(style = SpanStyle(fontSize = 21.sp)) {
                    append(text = "${car.carTopSpeed} Km/h")
                }

            })

            Text(text = buildAnnotatedString {

                withStyle(style = SpanStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)) {
                    append(text = "Acceleration: ")
                }

                withStyle(style = SpanStyle(fontSize = 21.sp)) {
                    append(text = "${car.carAcceleration} Seconds (0 to 100)")
                }

            })

        }

        Spacer(modifier = Modifier.height(height = 28.dp))

        Button(
            onClick = {
                carScreenViewModel.getCarDescription(car = car)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = CarizmaOrange,
                contentColor = Color.White
            )
        ) {
            Text(text = "Fun Fact!", style = MaterialTheme.typography.bodyLarge)
        }

        if (isResponseLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(all = 16.dp),
                color = Color.White,
                strokeWidth = 3.5.dp
            )
        } else {
            carDescription?.let {
                Text(
                    modifier = Modifier.padding(all = 7.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }

}