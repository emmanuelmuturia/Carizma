package emmanuelmuturia.carizma.player.domainlayer.repository

import emmanuelmuturia.carizma.car.domainlayer.model.Car

interface PlayerRepository {

    suspend fun getCarById(carId: Int): Car?

    suspend fun playCarAudio(carAudio: String)

    suspend fun pauseCarAudio()

    suspend fun rewindCarAudio()

    suspend fun fastForwardCarAudio()

}