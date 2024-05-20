package emmanuelmuturia.carizma.commons.dependencyinjection

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val firebaseKoinModule = module {

    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }

}