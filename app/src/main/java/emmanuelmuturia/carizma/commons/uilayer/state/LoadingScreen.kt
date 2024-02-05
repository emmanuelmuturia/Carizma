package emmanuelmuturia.carizma.commons.uilayer.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

            Text(
                modifier = Modifier.padding(start = 7.dp, end = 7.dp),
                text = "Uh oh! There seems to be an error there...",
                style = MaterialTheme.typography.bodyLarge
            )

        }

    }

}