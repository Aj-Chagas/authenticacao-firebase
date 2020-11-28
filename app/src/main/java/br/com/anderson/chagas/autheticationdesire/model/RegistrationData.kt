package br.com.anderson.chagas.autheticationdesire.model

import java.io.Serializable

class RegistrationData(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val birthDate: String,
    var streetName: String,
    var numberHouse: String,
    var complement: String,
    var postalCode: String,
    var state: String,
    var city: String,
    var latitude: Double,
    var longitude: Double
): Serializable