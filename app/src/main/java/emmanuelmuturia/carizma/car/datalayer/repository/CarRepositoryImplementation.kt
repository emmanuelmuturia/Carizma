package emmanuelmuturia.carizma.car.datalayer.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.car.domainlayer.repository.CarRepository
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CarRepositoryImplementation (
    private val generativeModel: GenerativeModel,
    private val homeRepository: HomeRepository
) : CarRepository {

    override suspend fun getCarById(carId: Int): Car? {
        return homeRepository.getCars().first().find { it.carId == carId }
    }

    override suspend fun getResponse(car: Car?): GenerateContentResponse {

        return generativeModel.generateContent(
            content {
                text(text = "Could you please tell me a fun fact about the ${car?.carName}?")
            }
        )

    }

    override suspend fun getResult(car: Car?): String? {
        return getResponse(car = car).text
    }

}