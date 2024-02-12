package emmanuelmuturia.carizma.home.domainlayer.repository

import emmanuelmuturia.carizma.car.domainlayer.Car

interface HomeRepository {

    suspend fun getCars(): List<Car>

}