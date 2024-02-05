package emmanuelmuturia.carizma.commons.uilayer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import emmanuelmuturia.carizma.R

@Composable
fun CarizmaBackgroundImage() {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.supercar),
        contentDescription = "Background Image",
        contentScale = ContentScale.FillBounds
    )

}