package br.com.anderson.chagas.autheticationdesire.service

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class FirebaseService(val auth: FirebaseAuth) {

    val tag = "firebase"

    fun login(email: String, password: String, onResult: (result: Boolean, msg: String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isComplete && it.isSuccessful) {
                onResult(it.isComplete && it.isSuccessful, "")
            } else {
                onResult(false, "${it.exception?.message}")
            }
        }
    }

    fun register(email: String, password: String, userName: String, onResult: (result: Boolean, msg: String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isComplete && it.isSuccessful) {
                auth.currentUser?.updateProfile(
                    UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(userName)
                    .build())
                Log.d(tag, "createUserWithEmail:success")
                onResult(true, "sucesso")
            } else {
                Log.w(tag, "createUserWithEmail:failure", it.exception)
                onResult(false, "${it.exception?.message}")
            }
        }
    }

    fun getUserId(): String = auth.currentUser?.uid ?: ""

    fun getUserName(): String = auth.currentUser?.displayName ?: ""

    fun logOut(onResult: () -> Unit) {
        auth.signOut()
        onResult()
    }
}