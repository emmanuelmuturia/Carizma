package emmanuelmuturia.carizma.search.uilayer

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application = application) {

    var searchItem = mutableStateOf(value = "")

    private val _searchResults = MutableStateFlow<List<Car>>(emptyList())
    val searchResults: StateFlow<List<Car>> = _searchResults.asStateFlow()

    fun searchCarByName(title: String, allMovies: List<Car>) {
        viewModelScope.launch {
            val results = searchCars(title, allMovies)
            _searchResults.value = results
        }
    }

    private fun searchCars(title: String, allCars: List<Car>): List<Car> {
        return allCars.filter { it.carName.contains(title, ignoreCase = true) }
    }

}