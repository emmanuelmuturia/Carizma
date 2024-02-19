package emmanuelmuturia.carizma.home.datalayer.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImplementation @Inject constructor(
    private val firestore: FirebaseFirestore
) : HomeRepository {

    override suspend fun getCars(): List<Car> {
        return firestore.collection("Cars").get().await().toObjects<Car>()
    }

}