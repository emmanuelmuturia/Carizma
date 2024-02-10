package emmanuelmuturia.carizma.search.uilayer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaHeader
import emmanuelmuturia.carizma.theme.CarizmaWhite
import emmanuelmuturia.carizma.theme.Caveat

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SearchScreen(navigateBack: () -> Unit, navigateToCar: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        val searchScreenViewModel: SearchScreenViewModel = hiltViewModel()

        var searchItem by rememberSaveable { mutableStateOf(value = "") }

        val result by searchScreenViewModel.searchResults.collectAsState()

        CarizmaBackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CarizmaHeader(navigateBack = navigateBack, headerTitle = "Search")
            Spacer(modifier = Modifier.height(height = 21.dp))
            OutlinedTextField(
                value = searchItem,
                onValueChange = { searchItem = it },
                shape = RoundedCornerShape(size = 21.dp),
                placeholder = {
                    Text(
                        text = "Enter Car Name e.g Porsche 911...", textAlign = TextAlign.Start,
                        fontFamily = Caveat,
                        color = CarizmaWhite,
                        fontWeight = FontWeight.Bold
                    )
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    cursorColor = Color.White
                )
            )

            if (result.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 7.dp)
                        .height(height = 250.dp), // Adjust the height as needed
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    GlideImage(
                        model = result.firstOrNull()?.carImage,
                        contentDescription = "Movie Poster",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = navigateToCar)
                    )
                }

                Text(
                    text = result.firstOrNull()?.carName.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Text(
                    text = "No results found.",
                    style = MaterialTheme.typography.titleLarge
                )
            }

        }

    }

}