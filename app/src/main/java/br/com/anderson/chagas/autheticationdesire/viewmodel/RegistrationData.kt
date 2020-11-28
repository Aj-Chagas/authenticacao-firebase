package br.com.anderson.chagas.autheticationdesire.viewmodel

import androidx.lifecycle.ViewModel
import br.com.anderson.chagas.autheticationdesire.helpers.isEmailValid
import br.com.anderson.chagas.autheticationdesire.helpers.isMinCharacter
import br.com.anderson.chagas.autheticationdesire.helpers.isMinCharacterToPhone
import br.com.anderson.chagas.autheticationdesire.model.RegistrationData
import br.com.anderson.chagas.autheticationdesire.repository.RegistrationDataRepository

class RegistrationDataViewModel(
    private val repository: RegistrationDataRepository
) : ViewModel(){

    fun salveInFirebase(registrationData: RegistrationData) {
        repository.saveInFirebase(registrationData)
    }

    fun minimumCharacterValidation(text: String, min: Int): Boolean {
        return !isMinCharacter(text, min)
    }

    fun validEmail(email: String) : Boolean {
        return !isEmailValid(email)
    }

    fun validationPhoneNumber(phoneNumer: String): Boolean {
        if (isMinCharacterToPhone(phoneNumer)) {
            return false
        }
        return true
    }

    fun validRegistrationFields(registrationData: RegistrationData): Boolean {
        return (isMinCharacter(registrationData.nome, 5) &&
                isMinCharacter(registrationData.sobrenome, 5) &&
                isMinCharacter(registrationData.dataAniversario, 5) &&
                isMinCharacterToPhone(registrationData.telefone) &&
                isEmailValid(registrationData.email))
    }

    fun validAllFields(registrationData: RegistrationData): Boolean {
        return (validRegistrationFields(registrationData) &&
                isMinCharacter(registrationData.rua!!, 5) &&
                isMinCharacter(registrationData.numero, 1) &&
                isMinCharacter(registrationData.complemento, 5) &&
                isMinCharacter(registrationData.cep, 8) &&
                isMinCharacter(registrationData.estado, 5) &&
                isMinCharacter(registrationData.cidade, 5) &&
                isMinCharacter(registrationData.latitude.toString(), 1) &&
                isMinCharacter(registrationData.longitude.toString(), 1))
    }
}