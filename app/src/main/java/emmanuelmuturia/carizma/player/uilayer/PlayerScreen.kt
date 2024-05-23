package emmanuelmuturia.carizma.player.uilayer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import emmanuelmuturia.carizma.R
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.commons.domainlayer.CarizmaState
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader
import emmanuelmuturia.carizma.home.uilayer.HomeScreenViewModel
import kotlin.math.absoluteValue

@Composable
fun PlayerScreen(
    navigateBack: () -> Unit,
    navigateToCarScreen: (Int) -> Unit,
    playerScreenViewModel: PlayerScreenViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    carId: Int?
) {

    LaunchedEffect(key1 = carId) {
        if (carId != null) {
            playerScreenViewModel.getCarById(carId = carId)
        }
    }

    val carizmaState by homeScreenViewModel.carizmaState.collectAsStateWithLifecycle()

    val carList = (carizmaState as CarizmaState.Success<List<Car>>).data

    val carState by playerScreenViewModel.carizmaCar.collectAsStateWithLifecycle()

    val player = playerScreenViewModel.player

    val currentPosition = playerScreenViewModel.currentPosition

    val sliderPosition = playerScreenViewModel.sliderPosition

    val totalDuration = playerScreenViewModel.totalDuration

    val currentSongIndex = playerScreenViewModel.currentSongIndex

    LaunchedEffect(key1 = player.currentPosition, key2 = player.isPlaying) {
        playerScreenViewModel.observePlayerPosition()
    }

    LaunchedEffect(key1 = currentPosition.longValue) {
        sliderPosition.longValue = currentPosition.longValue
    }

    LaunchedEffect(key1 = player.duration) {
        playerScreenViewModel.observePlayerDuration()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

        carState?.let { car ->
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
                        car.let { car ->
                            PlayerCarPager(
                                carList = carList,
                                navigateToCarScreen = { navigateToCarScreen(car.carId) },
                                player = player,
                                currentSongIndex = currentSongIndex,
                                playerScreenViewModel = playerScreenViewModel
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(height = 21.dp))
                    }

                    item {
                        PlayerAudioBar(
                            value = sliderPosition.longValue.toFloat(),
                            onValueChange = {
                                sliderPosition.longValue = it.toLong()
                            },
                            onValueChangeFinished = {
                                currentPosition.longValue = sliderPosition.longValue
                                player.seekTo(sliderPosition.longValue)
                            },
                            audioDuration = totalDuration.longValue.toFloat()
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(height = 21.dp))
                    }

                    item {
                        PlayerControls(
                            playerScreenViewModel = playerScreenViewModel,
                            carList = carList,
                            currentSongIndex = currentSongIndex
                        )
                    }

                }

            }
        }

    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerCarPager(
    carList: List<Car>,
    navigateToCarScreen: (Int) -> Unit,
    currentSongIndex: MutableIntState,
    player: ExoPlayer,
    playerScreenViewModel: PlayerScreenViewModel
) {

    val pagerState = rememberPagerState(pageCount = {
        carList.size
    })

    LaunchedEffect(key1 = pagerState.currentPage) {
        playerScreenViewModel.updateCurrentSongIndex(index = pagerState.currentPage)
        playerScreenViewModel.playCarAudio(carAudio = carList[currentSongIndex.intValue].carSound)
    }

    LaunchedEffect(key1 = player.currentMediaItemIndex) {
        playerScreenViewModel.updatePagerState()
    }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 65.dp),
        modifier = Modifier
    ) { page ->

        val pageOffset =
            (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

        val scaleFactor = 0.75f + (1f - 0.75f) * (1f - pageOffset.absoluteValue)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(modifier = Modifier
                .graphicsLayer {
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                .alpha(
                    scaleFactor.coerceIn(minimumValue = 0f, maximumValue = 1f)
                )
                .padding(10.dp)
                .clip(RoundedCornerShape(size = 16.dp))) {

                AsyncImage(
                    modifier = Modifier.clickable(onClick = { navigateToCarScreen(carList[page].carId) }),
                    model = carList[page].carImage,
                    contentDescription = "Player Car",
                    contentScale = ContentScale.FillBounds
                )

            }


        }

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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerControls(
    playerScreenViewModel: PlayerScreenViewModel,
    carList: List<Car>,
    currentSongIndex: MutableIntState
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(space = 7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                        if (playerScreenViewModel.isPlaying.value) {
                            playerScreenViewModel.pauseCarAudio()
                        } else if (playerScreenViewModel.player.currentMediaItem == null) {
                            playerScreenViewModel.playCarAudio(carAudio = carList[currentSongIndex.intValue].carSound)
                        } else {
                            playerScreenViewModel.resumeCarAudio()
                        }
                    }
                    .size(size = 42.dp),
                painter = painterResource(id = if (playerScreenViewModel.isPlaying.value) R.drawable.pause else R.drawable.play),
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

        Text(
            modifier = Modifier.basicMarquee(),
            text = "${carList[currentSongIndex.intValue].carName}${carList[currentSongIndex.intValue].carName}${carList[currentSongIndex.intValue].carName}",
            style = MaterialTheme.typography.titleLarge
        )

    }

}