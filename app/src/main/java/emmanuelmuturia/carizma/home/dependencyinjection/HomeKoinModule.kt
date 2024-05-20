package emmanuelmuturia.carizma.home.dependencyinjection

import emmanuelmuturia.carizma.home.datalayer.repository.HomeRepositoryImplementation
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import org.koin.dsl.module

val homeKoinModule = module {

    single<HomeRepository> {
        HomeRepositoryImplementation(firestore = get())
    }

}