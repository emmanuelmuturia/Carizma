package emmanuelmuturia.carizma.player.uilayer

import android.app.Application
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.player.domainlayer.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayerScreenViewModel(
    application: Application,
    private val playerRepository: PlayerRepository
) : AndroidViewModel(application = application) {

    private var _carizmaCar: MutableStateFlow<Car?> = MutableStateFlow(value = null)
    val carizmaCar: StateFlow<Car?> = _carizmaCar.asStateFlow()

    val player = playerRepository.player

    val isPlaying = mutableStateOf(value = false)

    val currentPosition = mutableLongStateOf(value = 0)

    val sliderPosition = mutableLongStateOf(value = 0)

    val totalDuration = mutableLongStateOf(value = 0)

    val currentSongIndex = mutableIntStateOf(value = 0)

    fun playCarAudio(carAudio: String) {
        viewModelScope.launch {
            playerRepository.playCarAudio(carAudio = carAudio)
            isPlaying.value = true
        }
    }

    fun resumeCarAudio() {
        viewModelScope.launch {
            playerRepository.resumeCarAudio()
            isPlaying.value = true
        }
    }

    fun pauseCarAudio() {
        viewModelScope.launch {
            playerRepository.pauseCarAudio()
            isPlaying.value = false
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

    fun observePlayerPosition() {
        viewModelScope.launch {
            while (true) {
                delay(timeMillis = 1000)
                currentPosition.longValue = withContext(context = Dispatchers.Main) {
                    player.currentPosition
                }
            }
        }
    }

    fun observePlayerDuration() {
        viewModelScope.launch {
            while (true) {
                withContext(context = Dispatchers.Main) {
                    if (player.duration > 0) {
                        totalDuration.longValue = player.duration
                    }
                }
            }
        }
    }

    fun updateCurrentSongIndex(index: Int) {
        viewModelScope.launch {
            currentSongIndex.intValue = index
            withContext(context = Dispatchers.Main) {
                player.seekTo(index, 0)
            }
        }
    }

    fun updatePagerState() {
        viewModelScope.launch {
            withContext(context = Dispatchers.Main) {
                currentSongIndex.intValue = player.currentMediaItemIndex
            }
        }
    }

}