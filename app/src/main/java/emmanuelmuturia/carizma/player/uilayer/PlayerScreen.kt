package emmanuelmuturia.carizma.player.uilayer

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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader
import emmanuelmuturia.carizma.theme.CarizmaWhite

@Composable
fun PlayerScreen(navigateBack: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CarizmaHeader(navigateBack = navigateBack, headerTitle = "Player")

            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 7.dp)) {

                item {
                    PlayerCar()
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
                    PlayerControls()
                }

            }

        }

    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerCar() {

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
                    model = "https://images.unsplash.com/photo-1594950195709-a14f66c242d7?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8bWNsYXJlbiUyMHAxfGVufDB8fDB8fHww",
                    contentDescription = "Player Car",
                    contentScale = ContentScale.Crop
                )

            }

        }

        Spacer(modifier = Modifier.width(width = 7.dp))

        Text(
            text = "McLaren P1",
            style = MaterialTheme.typography.titleLarge
        )

    }

}


@Composable
fun PlayerAudioBar() {

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp)
            .clip(shape = RoundedCornerShape(size = 21.dp)),
        thickness = 7.dp,
        color = CarizmaWhite
    )

}


@Composable
fun PlayerControls() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
        ,horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {}) {
            Icon(
                modifier = Modifier.size(size = 42.dp),
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = "Previous",
                tint = Color.White
            )
        }

        IconButton(onClick = {}) {
            Icon(
                modifier = Modifier.size(size = 42.dp),
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = "Play",
                tint = Color.White
            )
        }

        IconButton(onClick = {}) {
            Icon(
                modifier = Modifier.size(size = 42.dp),
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = "Next",
                tint = Color.White
            )
        }

    }

    Spacer(modifier = Modifier.height(height = 28.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        IconButton(onClick = {}) {
            Icon(
                modifier = Modifier.size(size = 42.dp),
                imageVector = Icons.Rounded.Refresh,
                contentDescription = "Garage",
                tint = Color.White
            )
        }

    }

}