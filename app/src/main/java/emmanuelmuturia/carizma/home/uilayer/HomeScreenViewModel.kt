package emmanuelmuturia.carizma.home.uilayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.commons.domainlayer.CarizmaState
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    application: Application,
    private val homeRepository: HomeRepository
): AndroidViewModel(application = application) {

    private var _carListState: MutableStateFlow<List<Car>> = MutableStateFlow(value = emptyList())
    val carListState: StateFlow<List<Car>> = _carListState.asStateFlow()

    private var _carizmaState: MutableStateFlow<CarizmaState<Any>> = MutableStateFlow(value = CarizmaState.Loading)
    val carizmaState: StateFlow<CarizmaState<Any>> = _carizmaState.asStateFlow()

    init {
        getCars()
    }

    private fun getCars() {
        viewModelScope.launch {

            _carizmaState.update { CarizmaState.Loading }

            try {
                _carListState.value = homeRepository.getCars()
                _carizmaState.update { CarizmaState.Success(data = _carListState.value) }
            } catch (e: Exception) {
                _carizmaState.update { CarizmaState.Error(message = e) }
            }

        }
    }

    fun getCarById(carId: Int?): Car? {
        return _carListState.value.find { it.carId == carId }
    }

}