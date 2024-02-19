package emmanuelmuturia.carizma.car.uilayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.car.domainlayer.repository.CarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CarScreenViewModel @Inject constructor(
    application: Application,
    private val carRepository: CarRepository
): AndroidViewModel(application = application) {

    private var _isResponseLoading = MutableStateFlow(value = false)
    val isResponseLoading: StateFlow<Boolean> = _isResponseLoading.asStateFlow()

    private var _carDescription: MutableStateFlow<String?> = MutableStateFlow(value = null)
    val carDescription: StateFlow<String?> = _carDescription.asStateFlow()

    fun getCarDescription(car: Car?) {
        viewModelScope.launch {
            try {
                _isResponseLoading.value = true
                _carDescription.value = carRepository.getResult(car = car)
            } catch (e: Exception) {
                Timber.tag(tag = "Gemini Error").e(message = "Could not get response due to: ${e.printStackTrace()}")
            } finally {
                _isResponseLoading.value = false
            }
        }
    }

}