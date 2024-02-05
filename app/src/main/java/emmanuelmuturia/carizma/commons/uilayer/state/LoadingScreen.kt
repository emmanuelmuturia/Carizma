package emmanuelmuturia.carizma.commons.uilayer.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage

@Composable
fun LoadingScreen() {

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 7.dp,
                strokeCap = StrokeCap.Square
            )

        }

    }

}