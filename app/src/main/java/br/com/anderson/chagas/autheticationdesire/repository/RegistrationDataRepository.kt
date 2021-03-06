package br.com.anderson.chagas.autheticationdesire.repository

import android.util.Log
import br.com.anderson.chagas.autheticationdesire.model.RegistrationData
import br.com.anderson.chagas.autheticationdesire.model.RemoteRegistration
import br.com.anderson.chagas.autheticationdesire.model.Session
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

class RegistrationDataRepository(
    private val db: FirebaseFirestore
) {
    fun saveInFirebase(registrationData: RegistrationData, onResult: (result: Boolean) -> Unit) {

        val registration = hashMapOf(
            "cep" to registrationData.cep,
            "complemento" to registrationData.complemento,
            "cidade" to registrationData.cidade,
            "dataAniversario" to registrationData.dataAniversario,
            "email" to registrationData.email,
            "estado" to registrationData.estado,
            "coordenadas" to GeoPoint(registrationData.latitude!!, registrationData.longitude!!),
            "nome" to registrationData.nome,
            "numero" to registrationData.numero,
            "rua" to registrationData.rua,
            "sobrenome" to registrationData.sobrenome,
            "telefone" to registrationData.telefone,
            "status" to true
        )

        db.collection("cadastro").document(Session.email!!)
            .set(registration)
            .addOnSuccessListener {
                onResult(true)
                Log.d("firebase", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener { e ->
                onResult(false)
                Log.w("firebase", "Error adding document", e)
            }

    }

    fun getRegistrationData( onResult: (result: RemoteRegistration?) -> Unit) {
        val docRef = db.collection("cadastro").document(Session.email!!)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    onResult(document.toObject(RemoteRegistration::class.java))
                    Log.d("firebase", "DocumentSnapshot data: ${document.data}")
                } else {
                    onResult(null)
                    Log.d("firebase", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                onResult(null)
                Log.d("firebase", "get failed with ", exception)
            }

    }

    fun disableAccount(onResult: (result: Boolean) -> Unit) {
        val docRef = db.collection("cadastro").document(Session.email!!)
        docRef.update("status", false)
            .addOnSuccessListener {
                onResult(true)
                Log.d("firebase", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener {
                    onResult(false)
                Log.w("firebase", "Error updating document", it) }
    }
}