package emmanuelmuturia.carizma.player.uilayer

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import emmanuelmuturia.carizma.R
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.commons.domainlayer.CarizmaState
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader
import emmanuelmuturia.carizma.commons.uilayer.state.ErrorScreen
import emmanuelmuturia.carizma.commons.uilayer.state.LoadingScreen
import emmanuelmuturia.carizma.commons.uilayer.theme.CarizmaWhite

@Composable
fun PlayerScreen(
    navigateBack: () -> Unit,
    navigateToCarScreen: (Int) -> Unit,
    carId: Int,
    playerScreenViewModel: PlayerScreenViewModel
) {

    var isPlaying: Boolean by rememberSaveable { mutableStateOf(value = false) }

    val car by playerScreenViewModel.carizmaCar.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = carId) {
        playerScreenViewModel.getCarById(carId = carId)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

        PlayerScreenElements(
            navigateBack = navigateBack,
            car = car,
            navigateToCarScreen = { navigateToCarScreen(carId) },
            isPlaying = isPlaying,
            togglePlayPause = { isPlaying = !isPlaying },
            playerScreenViewModel = playerScreenViewModel
        )

    }

}

@Composable
private fun PlayerScreenElements(
    navigateBack: () -> Unit,
    car: Car?,
    navigateToCarScreen: (Int) -> Unit,
    isPlaying: Boolean,
    togglePlayPause: () -> Unit,
    playerScreenViewModel: PlayerScreenViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        CarizmaHeader(navigateBack = navigateBack, headerTitle = "Player")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 7.dp)
        ) {

            item {
                car?.let { car ->
                    PlayerCar(
                        car = car,
                        navigateToCarScreen = { navigateToCarScreen(car.carId) })
                }
            }

            item {
                Spacer(modifier = Modifier.height(height = 21.dp))
            }

            item {
                PlayerAudioBar()
            }

            item {
                Spacer(modifier = Modifier.height(height = 21.dp))
            }

            item {
                car?.let {
                    PlayerControls(
                        playerScreenViewModel = playerScreenViewModel,
                        car = it,
                        isPlaying = isPlaying,
                        togglePlayPause = togglePlayPause
                    )
                }
            }

        }

    }
}


@Composable
fun PlayerCar(
    car: Car,
    navigateToCarScreen: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .height(height = 280.dp)
                .width(width = 280.dp)
                .clickable(onClick = { navigateToCarScreen(car.carId) }),
            shape = RoundedCornerShape(size = 21.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                AsyncImage(
                    model = car.carImage,
                    contentDescription = "Player Car",
                    contentScale = ContentScale.FillBounds
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
fun PlayerAudioBar() {

    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp)
            .clip(shape = RoundedCornerShape(size = 21.dp)),
        thickness = 7.dp,
        color = CarizmaWhite
    )

}


@Composable
fun PlayerControls(
    playerScreenViewModel: PlayerScreenViewModel,
    car: Car,
    isPlaying: Boolean,
    togglePlayPause: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier
                .clickable {
                    playerScreenViewModel.rewindCarAudio()
                }
                .size(size = 42.dp),
            painter = painterResource(id = R.drawable.rewind),
            contentDescription = "Replay Button",
            contentScale = ContentScale.Crop
        )

        Image(
            modifier = Modifier
                .clickable {
                    togglePlayPause()
                    if (isPlaying) {
                        playerScreenViewModel.pauseCarAudio()
                        !isPlaying
                    } else {
                        playerScreenViewModel.playCarAudio(carAudio = car.carSound)
                        !isPlaying
                    }
                }
                .size(size = 42.dp),
            painter = painterResource(id = if (isPlaying) R.drawable.pause else R.drawable.play),
            contentDescription = "Play/Pause Button",
            contentScale = ContentScale.Crop
        )

        Image(
            modifier = Modifier
                .clickable {
                    playerScreenViewModel.fastForwardCarAudio()
                }
                .size(size = 42.dp),
            painter = painterResource(id = R.drawable.forward),
            contentDescription = "Forward Button",
            contentScale = ContentScale.Crop
        )

    }

}