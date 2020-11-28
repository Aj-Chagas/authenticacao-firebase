package br.com.anderson.chagas.autheticationdesire.repository

import android.util.Log
import br.com.anderson.chagas.autheticationdesire.model.RegistrationData
import br.com.anderson.chagas.autheticationdesire.model.Session
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

class RegistrationDataRepository(
    private val db: FirebaseFirestore
) {
    fun saveInFirebase(registrationData: RegistrationData) {

        val registration = hashMapOf(
            "cep" to registrationData.address?.postalCode,
            "cidade" to registrationData.address?.city,
            "data_aniversario" to registrationData.birthDate,
            "email" to registrationData.email,
            "estado" to registrationData.address?.state,
            "lat-lon" to GeoPoint(registrationData.address?.latitude!!, registrationData.address.longitude),
            "nome" to registrationData.firstName,
            "numero" to registrationData.address?.numberHouse,
            "rua" to registrationData.address?.streetName,
            "sobrenome" to registrationData.lastName,
            "telefone" to registrationData.phone

        )

        db.collection("cadastro").document(Session.email!!)
            .set(registration)
            .addOnSuccessListener { _ ->
                Log.d("firebase", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener { e ->
                Log.w("firebase", "Error adding document", e)
            }

    }

    fun getRegistrationData() {
        val docRef = db.collection("cadastro").document(Session.email!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("firebase", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("firebase", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("firebase", "get failed with ", exception)
            }

    }
}