package emmanuelmuturia.carizma.player.uilayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.carizma.player.domainlayer.repository.PlayerRepository
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