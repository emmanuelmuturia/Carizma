package emmanuelmuturia.carizma.home.datalayer.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class HomeRepositoryImplementation(
    private val firebaseFirestore: FirebaseFirestore
) : HomeRepository {

    override fun getCars(): Flow<List<Car>> = flow {
        emit(value = firebaseFirestore.collection("Cars").get().await().toObjects<Car>())
    }

}