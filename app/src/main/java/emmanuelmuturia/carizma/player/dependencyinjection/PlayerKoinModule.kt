package emmanuelmuturia.carizma.player.dependencyinjection

import emmanuelmuturia.carizma.player.datalayer.repository.PlayerRepositoryImplementation
import emmanuelmuturia.carizma.player.domainlayer.repository.PlayerRepository
import emmanuelmuturia.carizma.player.uilayer.PlayerScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val playerKoinModule = module {

    single<PlayerRepository> {
        PlayerRepositoryImplementation(
            context = androidApplication().applicationContext
        )
    }

    single<PlayerScreenViewModel> {
        PlayerScreenViewModel(
            application = androidApplication(),
            playerRepository = get(),
            homeRepository = get()
        )
    }

}