package emmanuelmuturia.carizma.player.dependencyinjection

import android.content.Context
import emmanuelmuturia.carizma.player.datalayer.repository.PlayerRepositoryImplementation
import emmanuelmuturia.carizma.player.domainlayer.repository.PlayerRepository
import emmanuelmuturia.carizma.player.uilayer.PlayerScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val playerKoinModule = module {

    single<Context> {
        androidContext().applicationContext
    }

    single<PlayerRepository> {
        PlayerRepositoryImplementation(
            context = get()
        )
    }

    single<PlayerScreenViewModel> {
        PlayerScreenViewModel(
            application = androidApplication(),
            playerRepository = get()
        )
    }

}