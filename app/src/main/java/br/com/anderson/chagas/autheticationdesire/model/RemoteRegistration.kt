package br.com.anderson.chagas.autheticationdesire.model

import com.google.firebase.firestore.GeoPoint
import java.io.Serializable

data class RemoteRegistration(
    val nome: String?,
    val sobrenome: String?,
    val email: String?,
    val telefone: String?,
    val dataAniversario: String?,
    var rua: String?,
    var numero: String?,
    var complemento: String?,
    var cep: String?,
    var estado: String?,
    var cidade: String?,
    var coordenadas: GeoPoint?,
    val status: Boolean? = true
): Serializable {
    constructor() : this("", "", "", "",
    "", "", "", "", "", "",
        "", GeoPoint(0.0,0.0), true
    )
}