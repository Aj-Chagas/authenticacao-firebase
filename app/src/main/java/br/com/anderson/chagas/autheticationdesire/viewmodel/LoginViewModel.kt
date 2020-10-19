package br.com.anderson.chagas.autheticationdesire.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.autheticationdesire.model.User
import br.com.anderson.chagas.autheticationdesire.service.FirebaseService

class LoginViewModel(
    private val firebaseService: FirebaseService
): ViewModel() {

    val loginliveData =  MutableLiveData<String>()

    fun singup(user: User) {
        firebaseService.register(user.email, user.password, user.name) { result: Boolean, message: String ->
            if (result) {
                loginliveData.postValue("cadastro efetuado com sucesso")
            } else {
                loginliveData.postValue("$message")
            }
        }
    }

    fun singin(user: User) {
        firebaseService.login(user.email, user.password) {result: Boolean ->
            if (result) {
                loginliveData.postValue("Login efetuado com sucesso")
            } else {
                loginliveData.postValue("")
            }
        }
    }
}