package emmanuelmuturia.carizma.car.dependencyinjection

import com.google.ai.client.generativeai.GenerativeModel
import emmanuelmuturia.carizma.BuildConfig
import emmanuelmuturia.carizma.car.datalayer.repository.CarRepositoryImplementation
import emmanuelmuturia.carizma.car.domainlayer.repository.CarRepository
import emmanuelmuturia.carizma.car.uilayer.CarScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val carKoinModule = module {

    single<GenerativeModel> {
        GenerativeModel(modelName = "gemini-pro", apiKey = BuildConfig.geminiApiKey)
    }

    single<CarRepository> {
        CarRepositoryImplementation(generativeModel = get())
    }

    viewModel {
        CarScreenViewModel(
            application = androidApplication(),
            carRepository = get()
        )
    }

}