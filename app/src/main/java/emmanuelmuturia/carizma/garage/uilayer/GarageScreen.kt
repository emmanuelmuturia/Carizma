package emmanuelmuturia.carizma.garage.uilayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import emmanuelmuturia.carizma.R
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.commons.uilayer.components.CarizmaBackgroundImage
import emmanuelmuturia.carizma.commons.uilayer.components.porscheList

@Composable
fun GarageScreen(navigateBack: () -> Unit) {

    val isVertical: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(value = true)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        CarizmaBackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            GarageScreenHeader(
                navigateBack = navigateBack,
                headerTitle = "My Garage",
                verticalToHorizontal = { isVertical.value = !isVertical.value })

            if (isVertical.value) VerticalGarageCars() else HorizontalGarageCars()

        }

    }

}


@Composable
fun GarageScreenHeader(
    navigateBack: () -> Unit,
    headerTitle: String,
    verticalToHorizontal: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp, start = 10.dp, bottom = 21.dp, end = 14.dp)
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(onClick = navigateBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Arrow Back",
                tint = Color.White
            )
        }


        Text(
            text = headerTitle,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 25.sp
        )

        Image(
            modifier = Modifier.clickable(onClick = verticalToHorizontal),
            painter = painterResource(id = R.drawable.grid),
            contentDescription = "Grid View"
        )

    }

}


@Composable
fun VerticalGarageCars() {

    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(count = 2)) {
        items(items = porscheList) { car ->
            CarItem(car = car)
        }
    }

}


@Composable
fun HorizontalGarageCars() {

    LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        items(items = porscheList) { car ->
            CarItem(car = car)
        }
    }

}


@Composable
fun CarItem(car: Car) {

    Column(
        modifier = Modifier.padding(start = 14.dp)
    ) {

        Card(
            modifier = Modifier
                .height(height = 140.dp)
                .width(width = 140.dp)
                .clickable {

                }, shape = RoundedCornerShape(size = 21.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                AsyncImage(
                    model = car.carImage,
                    contentDescription = "The Garage Car",
                    contentScale = ContentScale.Crop
                )

            }

        }

        Spacer(modifier = Modifier.height(height = 7.dp))

        Text(
            text = car.carName,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(height = 21.dp))

    }

}