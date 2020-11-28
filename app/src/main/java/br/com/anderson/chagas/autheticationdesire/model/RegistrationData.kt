package br.com.anderson.chagas.autheticationdesire.model

import com.google.firebase.firestore.GeoPoint
import java.io.Serializable

class RegistrationData(
    val nome: String,
    val sobrenome: String,
    val email: String,
    val telefone: String,
    val dataAniversario: String,
    var rua: String,
    var numero: String,
    var complemento: String,
    var cep: String,
    var estado: String,
    var cidade: String,
    var latitude: Double,
    var longitude: Double
): Serializable{

}