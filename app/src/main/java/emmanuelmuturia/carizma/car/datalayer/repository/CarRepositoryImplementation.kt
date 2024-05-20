package emmanuelmuturia.carizma.car.datalayer.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.car.domainlayer.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImplementation (
    private val generativeModel: GenerativeModel
) : CarRepository {

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