package br.com.anderson.chagas.autheticationdesire.viewmodel

import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.autheticationdesire.repository.RegistrationDataRepository
import com.google.firebase.firestore.FirebaseFirestore

class RegistrationDataViewModel(
    val repository: RegistrationDataRepository
) : ViewModel(){
    fun salveInFirebase() {
        val user = hashMapOf(
            "nome" to "Alan"
        )

        repository.saveInFirebase(user)
    }
}