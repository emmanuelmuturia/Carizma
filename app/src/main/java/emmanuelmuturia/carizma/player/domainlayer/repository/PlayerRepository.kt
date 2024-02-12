package emmanuelmuturia.carizma.player.domainlayer.repository

import android.content.Context
import androidx.media3.common.MediaItem

interface PlayerRepository {

    suspend fun playCarAudio(carAudio: String)

    suspend fun pauseCarAudio()

}