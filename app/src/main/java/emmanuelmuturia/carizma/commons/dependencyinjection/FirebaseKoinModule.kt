package emmanuelmuturia.carizma.commons.dependencyinjection

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import org.koin.dsl.module

val firebaseKoinModule = module {

    single<FirebaseFirestore> {
        Firebase.firestore
    }

}