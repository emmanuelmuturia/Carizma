package emmanuelmuturia.carizma.home.dependencyinjection

import emmanuelmuturia.carizma.home.datalayer.repository.HomeRepositoryImplementation
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import emmanuelmuturia.carizma.home.uilayer.HomeScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeKoinModule = module {

    single<HomeRepository> {
        HomeRepositoryImplementation(firebaseFirestore = get())
    }

    viewModel {
        HomeScreenViewModel(
            application = androidApplication(),
            homeRepository = get()
        )
    }

}