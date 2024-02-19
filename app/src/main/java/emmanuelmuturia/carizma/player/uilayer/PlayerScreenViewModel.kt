package emmanuelmuturia.carizma.player.uilayer

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.carizma.car.domainlayer.Car
import emmanuelmuturia.carizma.player.domainlayer.repository.PlayerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerScreenViewModel @Inject constructor(
    application: Application,
    private val playerRepository: PlayerRepository
): AndroidViewModel(application = application) {

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

}