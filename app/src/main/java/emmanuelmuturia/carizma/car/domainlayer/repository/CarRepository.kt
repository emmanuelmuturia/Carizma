package emmanuelmuturia.carizma.car.domainlayer.repository

import com.google.ai.client.generativeai.type.GenerateContentResponse
import emmanuelmuturia.carizma.car.domainlayer.model.Car

interface CarRepository {

    suspend fun getResponse(car: Car?): GenerateContentResponse

    suspend fun getResult(car: Car?): String?

}