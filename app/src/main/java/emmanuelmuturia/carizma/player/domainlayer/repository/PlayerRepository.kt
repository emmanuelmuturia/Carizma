package emmanuelmuturia.carizma.player.domainlayer.repository

import androidx.media3.exoplayer.ExoPlayer
import emmanuelmuturia.carizma.car.domainlayer.model.Car

interface PlayerRepository {

    val player: ExoPlayer

    suspend fun getCarById(carId: Int): Car?

    suspend fun playCarAudio(carAudio: String)

    suspend fun pauseCarAudio()

    suspend fun rewindCarAudio()

    suspend fun fastForwardCarAudio()

}