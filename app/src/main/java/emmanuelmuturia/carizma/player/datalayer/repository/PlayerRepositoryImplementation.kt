package emmanuelmuturia.carizma.player.datalayer.repository

import android.content.Context
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import emmanuelmuturia.carizma.player.domainlayer.repository.PlayerRepository
import kotlinx.coroutines.flow.first

class PlayerRepositoryImplementation(
    private val context: Context,
    private val homeRepository: HomeRepository
) : PlayerRepository {

    override val player by lazy { ExoPlayer.Builder(context).build() }

    override suspend fun getCarById(carId: Int): Car? {
        return homeRepository.getCars().first().find { it.carId == carId }
    }

    override suspend fun playCarAudio(carAudio: String) {
        val mediaItem: MediaItem = MediaItem.fromUri(carAudio.toUri())
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override suspend fun resumeCarAudio() {
        player.playWhenReady = true
    }

    override suspend fun pauseCarAudio() {
        player.playWhenReady = false
    }

    override suspend fun rewindCarAudio() {
        player.seekTo(player.currentPosition - 1000)
    }

    override suspend fun fastForwardCarAudio() {
        player.seekTo(player.currentPosition + 1000)
    }

}