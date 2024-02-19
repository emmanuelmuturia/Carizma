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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import emmanuelmuturia.carizma.R
import emmanuelmuturia.carizma.car.domainlayer.Car
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader
import emmanuelmuturia.carizma.home.uilayer.HomeScreenViewModel
import emmanuelmuturia.carizma.theme.CarizmaWhite

@Composable
fun PlayerScreen(navigateBack: () -> Unit, carId: Int?) {

    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

    val playerScreenViewModel: PlayerScreenViewModel = hiltViewModel()

    val car = homeScreenViewModel.getCarById(carId = carId)

    var isPlaying: Boolean by rememberSaveable { mutableStateOf(value = false) }

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

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
                    if (car != null) {
                        PlayerCar(car = car)
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
                    if (car != null) {
                        PlayerControls(
                            playerScreenViewModel = playerScreenViewModel,
                            car = car,
                            isPlaying = isPlaying,
                            togglePlayPause = { isPlaying = !isPlaying }
                        )
                    }
                }

            }

        }

    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerCar(
    car: Car
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
                .clickable {

                }, shape = RoundedCornerShape(size = 21.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {


                GlideImage(
                    model = car.carImage,
                    contentDescription = "Player Car",
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
fun PlayerControls(playerScreenViewModel: PlayerScreenViewModel, car: Car, isPlaying: Boolean, togglePlayPause: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier
                .clickable {

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

                }
                .size(size = 42.dp),
            painter = painterResource(id = R.drawable.forward),
            contentDescription = "Forward Button",
            contentScale = ContentScale.Crop
        )

    }

}