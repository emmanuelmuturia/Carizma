package emmanuelmuturia.carizma.home.uilayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.commons.domainlayer.CarizmaState
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel (
    application: Application,
    private val homeRepository: HomeRepository
): AndroidViewModel(application = application) {

    private var _carizmaState: MutableStateFlow<CarizmaState<List<Car>>> = MutableStateFlow(value = CarizmaState.Loading)
    val carizmaState: StateFlow<CarizmaState<List<Car>>> = _carizmaState.asStateFlow()

    init {
        getCars()
    }

    private fun getCars() {
        viewModelScope.launch {

            try {
                homeRepository.getCars().collect { carList ->
                    _carizmaState.update { CarizmaState.Success(data = carList) }
                }
            } catch (e: Exception) {
                _carizmaState.update { CarizmaState.Error(message = e) }
            }

        }
    }

    suspend fun getCarById(carId: Int?): Car? {
        return homeRepository.getCars()
            .map { carList -> carList.find { it.carId == carId } }
            .firstOrNull { it != null }
    }

}