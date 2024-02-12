package emmanuelmuturia.carizma.player.datalayer.repository

import android.content.Context
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import emmanuelmuturia.carizma.player.domainlayer.repository.PlayerRepository
import javax.inject.Inject

class PlayerRepositoryImplementation @Inject constructor(val context: Context) : PlayerRepository {

    private val player by lazy { ExoPlayer.Builder(context).build() }

    override suspend fun playCarAudio(carAudio: String) {
        val mediaItem: MediaItem = MediaItem.fromUri(carAudio.toUri())
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override suspend fun pauseCarAudio() {
        player.pause()
        //player.release()
    }

}