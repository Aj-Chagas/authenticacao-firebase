package br.com.anderson.chagas.autheticationdesire.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

class RegistrationDataRepository(
    private val db: FirebaseFirestore
) {
    fun saveInFirebase(user: HashMap<String, String>) {
        db.collection("teste").document("andersonMaster")
            .set(user)
            .addOnSuccessListener { _ ->
                Log.d("firebase", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener { e ->
                Log.w("firebase", "Error adding document", e)
            }

    }
}