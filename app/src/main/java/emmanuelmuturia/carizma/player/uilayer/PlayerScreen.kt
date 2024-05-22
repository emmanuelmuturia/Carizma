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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import emmanuelmuturia.carizma.R
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader
import kotlinx.coroutines.delay

@Composable
fun PlayerScreen(
    navigateBack: () -> Unit,
    navigateToCarScreen: (Int) -> Unit,
    playerScreenViewModel: PlayerScreenViewModel,
    carId: Int?
) {

    LaunchedEffect(key1 = carId) {
        if (carId != null) {
            playerScreenViewModel.getCarById(carId = carId)
        }
    }

    val carState by playerScreenViewModel.carizmaCar.collectAsStateWithLifecycle()

    val player = playerScreenViewModel.player

    var isPlaying = playerScreenViewModel.isPlaying.value

    val currentPosition = playerScreenViewModel.currentPosition

    val sliderPosition = playerScreenViewModel.sliderPosition

    val totalDuration = playerScreenViewModel.totalDuration

    LaunchedEffect(key1 = player.currentPosition, key2 = player.isPlaying) {
        delay(timeMillis = 1000)
        currentPosition.longValue = player.currentPosition
    }

    LaunchedEffect(key1 = currentPosition.longValue) {
        sliderPosition.longValue = currentPosition.longValue
    }

    LaunchedEffect(key1 = player.duration) {
        if (player.duration > 0) {
            totalDuration.longValue = player.duration
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

        carState?.let { car ->
            PlayerScreenElements(
                navigateBack = navigateBack,
                car = car,
                navigateToCarScreen = { navigateToCarScreen(car.carId) },
                togglePlayPause = { isPlaying = !isPlaying },
                playerScreenViewModel = playerScreenViewModel,
                isPlaying = isPlaying,
                player = player,
                currentPosition = currentPosition,
                sliderPosition = sliderPosition,
                totalDuration = totalDuration
            )
        }

    }

}

@Composable
private fun PlayerScreenElements(
    navigateBack: () -> Unit,
    car: Car?,
    navigateToCarScreen: (Int) -> Unit,
    togglePlayPause: () -> Unit,
    playerScreenViewModel: PlayerScreenViewModel,
    player: ExoPlayer,
    currentPosition: MutableState<Long>,
    sliderPosition: MutableState<Long>,
    totalDuration: MutableState<Long>,
    isPlaying: Boolean
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
                PlayerAudioBar(
                    value = sliderPosition.value.toFloat(),
                    onValueChange = {
                        sliderPosition.value = it.toLong()
                    },
                    onValueChangeFinished = {
                        currentPosition.value = sliderPosition.value
                        player.seekTo(sliderPosition.value)
                    },
                    audioDuration = totalDuration.value.toFloat()
                )
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
fun PlayerAudioBar(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    audioDuration: Float,
    colors: SliderColors = SliderDefaults.colors(
        thumbColor = Color.White,
        activeTrackColor = Color.White,
        inactiveTrackColor = Color.White.copy(alpha = 0.21f)
    )
) {

    Slider(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        valueRange = 0f..audioDuration,
        colors = colors,
        onValueChangeFinished = onValueChangeFinished
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