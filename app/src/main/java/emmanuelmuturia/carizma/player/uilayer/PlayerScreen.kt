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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader

@Composable
fun PlayerScreen() {

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CarizmaHeader(navigateBack = { /*TODO*/ }, headerTitle = "")

            Spacer(modifier = Modifier.height(height = 7.dp))

            PlayerPhoto()

            Spacer(modifier = Modifier.height(height = 7.dp))

            PlayerBar()

            Spacer(modifier = Modifier.height(height = 7.dp))

            PlayBackMenu()

        }

    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerPhoto() {

    Box(modifier = Modifier.size(width = 291.dp, height = 291.dp)) {
        GlideImage(model = "", contentDescription = "Player Photo", contentScale = ContentScale.Crop)
    }

    Spacer(modifier = Modifier.height(height = 3.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "McLaren P1", style = MaterialTheme.typography.labelLarge)
    }

}

@Composable
fun PlayerBar() {



}


@Composable
fun PlayBackMenu() {

    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 14.dp, end = 14.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .size(size = 35.dp)
                .clickable(onClick = {

                }),
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = "Rewind",
            tint = Color.White
        )

        Icon(
            modifier = Modifier
                .size(size = 35.dp)
                .clickable(onClick = {

                }),
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = "Play",
            tint = Color.White
        )

        Icon(
            modifier = Modifier
                .size(size = 35.dp)
                .clickable(onClick = {

                }),
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
            contentDescription = "Fast Forward",
            tint = Color.White
        )

    }

    Spacer(modifier = Modifier.height(height = 21.dp))

    Icon(
        modifier = Modifier
            .size(size = 35.dp)
            .clickable(onClick = {

            }),
        imageVector = Icons.Rounded.Refresh,
        contentDescription = "Play",
        tint = Color.White
    )

    Spacer(modifier = Modifier.height(height = 21.dp))

    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 14.dp, end = 14.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .size(size = 35.dp)
                .clickable(onClick = {

                }),
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "Rewind",
            tint = Color.White
        )

        Icon(
            modifier = Modifier
                .size(size = 35.dp)
                .clickable(onClick = {

                }),
            imageVector = Icons.Rounded.Home,
            contentDescription = "Play",
            tint = Color.White
        )

        Icon(
            modifier = Modifier
                .size(size = 35.dp)
                .clickable(onClick = {

                }),
            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
            contentDescription = "Fast Forward",
            tint = Color.White
        )

    }

}