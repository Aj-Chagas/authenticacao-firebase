package br.com.anderson.chagas.autheticationdesire.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.autheticationdesire.helpers.isEmailValid
import br.com.anderson.chagas.autheticationdesire.helpers.isPasswordValid
import br.com.anderson.chagas.autheticationdesire.model.User
import br.com.anderson.chagas.autheticationdesire.service.FirebaseService
import com.google.firebase.firestore.FirebaseFirestore

class LoginViewModel(
    private val firebaseService: FirebaseService
): ViewModel() {

    val loginliveData =  MutableLiveData<String>()
    val sucessLogin = MutableLiveData<String>()

    fun singup(user: User) {
        if (validFields(user)) {
            registerInFirebase(user)
        } else {
            loginliveData.postValue("preencha todos os campos corretamente")
        }
    }

    private fun registerInFirebase(user: User) {
        firebaseService.register(
            user.email,
            user.password,
            ""
        ) { result: Boolean, message: String ->
            if (result) {
                sucessLogin.postValue("cadastro efetuado com sucesso")
            } else {
                loginliveData.postValue("$message")
            }
        }
    }

    fun signin(user: User) {
        if (validFields(user)) {
            signinFirebase(user)
        } else {
            loginliveData.postValue("preencha todos os campos corretamente")
        }
    }

    private fun signinFirebase(user: User) {
        firebaseService.login(user.email, user.password) { result: Boolean, message: String ->
            if (result) {
                sucessLogin.postValue("Login efetuado com sucesso")
            } else {
                loginliveData.postValue("$message")
            }
        }
    }

    fun validEmail(email: String) : Boolean {
        return !isEmailValid(email)
    }

    fun validPassword(password: String): Boolean {
        return !(isPasswordValid(password))
    }

    fun validFields(user: User): Boolean {
        return (isPasswordValid(user.password)) && isEmailValid(user.email)
    }
}