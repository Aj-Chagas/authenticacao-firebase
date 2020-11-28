package br.com.anderson.chagas.autheticationdesire.repository

import android.util.Log
import br.com.anderson.chagas.autheticationdesire.model.RegistrationData
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

        db.collection("cadastro").document("anderson.reis92@gmail.com")
            .set(registration)
            .addOnSuccessListener { _ ->
                Log.d("firebase", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener { e ->
                Log.w("firebase", "Error adding document", e)
            }

    }
}