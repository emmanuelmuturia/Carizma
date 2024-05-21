package emmanuelmuturia.carizma.player.uilayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.commons.domainlayer.CarizmaState
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import emmanuelmuturia.carizma.player.domainlayer.repository.PlayerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerScreenViewModel (
    application: Application,
    private val playerRepository: PlayerRepository
): AndroidViewModel(application = application) {

    private var _carizmaCar: MutableStateFlow<Car?> = MutableStateFlow(value = null)
    val carizmaCar: StateFlow<Car?> = _carizmaCar.asStateFlow()

    fun playCarAudio(carAudio: String) {
        viewModelScope.launch {
            playerRepository.playCarAudio(carAudio = carAudio)
        }
    }

    fun pauseCarAudio() {
        viewModelScope.launch {
            playerRepository.pauseCarAudio()
        }
    }

    fun rewindCarAudio() {
        viewModelScope.launch {
            playerRepository.rewindCarAudio()
        }
    }

    fun fastForwardCarAudio() {
        viewModelScope.launch {
            playerRepository.fastForwardCarAudio()
        }
    }

    fun getCarById(carId: Int) {
        viewModelScope.launch {
            _carizmaCar.value = playerRepository.getCarById(carId = carId)
        }
    }

}