package emmanuelmuturia.carizma.home.datalayer.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import emmanuelmuturia.carizma.car.domainlayer.model.Car
import emmanuelmuturia.carizma.home.domainlayer.repository.HomeRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class HomeRepositoryImplementation(
    private val firebaseFirestore: FirebaseFirestore
) : HomeRepository {

    override fun getCars(): Flow<List<Car>> = callbackFlow {
        firebaseFirestore.collection("Cars").addSnapshotListener { value, error ->

            if (error != null) {
                return@addSnapshotListener
            }

            if (value != null && !value.isEmpty) {
                trySend(element = value.toObjects<Car>()).isSuccess
            }

        }

        awaitClose { this.cancel(message = "Could not fetch your cars...") }

    }

}