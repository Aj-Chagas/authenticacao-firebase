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
        return (isMinCharacter(registrationData.firstName, 5) &&
                isMinCharacter(registrationData.lastName, 5) &&
                isMinCharacter(registrationData.birthDate, 5) &&
                isMinCharacterToPhone(registrationData.phone) &&
                isEmailValid(registrationData.email))
    }

    fun validAllFields(registrationData: RegistrationData): Boolean {
        return (validRegistrationFields(registrationData) &&
                isMinCharacter(registrationData.streetName!!, 5) &&
                isMinCharacter(registrationData.numberHouse, 1) &&
                isMinCharacter(registrationData.complement, 5) &&
                isMinCharacter(registrationData.postalCode, 8) &&
                isMinCharacter(registrationData.state, 5) &&
                isMinCharacter(registrationData.city, 5) &&
                isMinCharacter(registrationData.latitude.toString(), 1) &&
                isMinCharacter(registrationData.longitude.toString(), 1))
    }
}