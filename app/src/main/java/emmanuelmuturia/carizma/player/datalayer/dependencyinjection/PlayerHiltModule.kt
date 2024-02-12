package emmanuelmuturia.carizma.player.datalayer.dependencyinjection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.carizma.player.datalayer.repository.PlayerRepositoryImplementation
import emmanuelmuturia.carizma.player.domainlayer.repository.PlayerRepository

@Module
@InstallIn(SingletonComponent::class)
object PlayerHiltModule {

    @Provides
    fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun providesPlayerRepository(context: Context): PlayerRepository {
        return PlayerRepositoryImplementation(context = context)
    }

}