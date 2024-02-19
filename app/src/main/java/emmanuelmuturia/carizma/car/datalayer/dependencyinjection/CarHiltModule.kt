package emmanuelmuturia.carizma.car.datalayer.dependencyinjection

import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.carizma.BuildConfig
import emmanuelmuturia.carizma.car.datalayer.repository.CarRepositoryImplementation
import emmanuelmuturia.carizma.car.domainlayer.repository.CarRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarHiltModule {

    @Provides
    @Singleton
    fun providesGenerativeModel(): GenerativeModel {
        return GenerativeModel(modelName = "gemini-pro", apiKey = BuildConfig.geminiApiKey)
    }

    @Provides
    @Singleton
    fun providesCarRepository(generativeModel: GenerativeModel): CarRepository {
        return CarRepositoryImplementation(generativeModel = generativeModel)
    }

}