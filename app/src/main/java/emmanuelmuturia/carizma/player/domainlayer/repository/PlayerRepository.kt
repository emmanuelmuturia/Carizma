package emmanuelmuturia.carizma.player.domainlayer.repository

interface PlayerRepository {

    suspend fun playCarAudio(carAudio: String)

    suspend fun pauseCarAudio()

    suspend fun rewindCarAudio()

    suspend fun fastForwardCarAudio()

}