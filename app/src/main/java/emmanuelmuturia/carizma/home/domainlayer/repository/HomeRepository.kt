package emmanuelmuturia.carizma.home.domainlayer.repository

import emmanuelmuturia.carizma.car.domainlayer.model.Car

interface HomeRepository {

    suspend fun getCars(): List<Car>

}