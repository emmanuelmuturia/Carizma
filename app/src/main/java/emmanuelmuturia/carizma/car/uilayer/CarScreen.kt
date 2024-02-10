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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader

@Composable
fun CarScreen() {

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CarizmaHeader(navigateBack = { /*TODO*/ }, headerTitle = "McLaren P1")

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                item {
                    CarDetails()
                }

            }

        }

    }

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CarDetails() {

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

        Spacer(modifier = Modifier.height(height = 28.dp))

        Text(
            text = "McLaren P1",
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
                    append(text = "320 Km/h")
                }

            })

            Text(text = buildAnnotatedString {

                withStyle(style = SpanStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)) {
                    append(text = "Acceleration: ")
                }

                withStyle(style = SpanStyle(fontSize = 21.sp)) {
                    append(text = "0 to 100 (3.5 Seconds)")
                }

            })

        }

        Spacer(modifier = Modifier.height(height = 28.dp))

        Text(
            modifier = Modifier.padding(all = 7.dp),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(height = 21.dp))

    }

}