package emmanuelmuturia.carizma.home.datalayer.dependencyinjection

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.carizma.home.datalayer.repository.HomeRepositoryImplementation
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository

@Module
@InstallIn(SingletonComponent::class)
object HomeHiltModule {

    @Provides
    fun providesHomeRepository(firestore: FirebaseFirestore): HomeRepository {
        return HomeRepositoryImplementation(firestore = firestore)
    }

}