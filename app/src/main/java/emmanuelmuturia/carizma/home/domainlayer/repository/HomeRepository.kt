package emmanuelmuturia.carizma.home.domainlayer.repository

import emmanuelmuturia.carizma.car.domainlayer.model.Car
import kotlinx.coroutines.flow.Flow

fun interface HomeRepository {

    fun getCars(): Flow<List<Car>>

}