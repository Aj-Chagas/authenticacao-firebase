package br.com.anderson.chagas.autheticationdesire.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import br.com.anderson.chagas.autheticationdesire.R
import br.com.anderson.chagas.autheticationdesire.helpers.setError
import br.com.anderson.chagas.autheticationdesire.model.RegistrationData
import br.com.anderson.chagas.autheticationdesire.viewmodel.RegistrationDataViewModel
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_edit.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddressActivity : AppCompatActivity() {

    private val viewModel: RegistrationDataViewModel by viewModel()

    private var registrationData: RegistrationData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        setupToolbar()

        registrationData = intent.getSerializableExtra("EXTRA_REGISTRATION") as? RegistrationData

        address_street_name.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 5)
            if (error) setError(true, address_street_name, "mínimo 5 caracteres")
            else setError(false, address_street_name, null)
        }

        address_street_number.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 1)
            if (error) setError(true, address_street_number, "mínimo 1 caracteres")
            else setError(false, address_street_number, null)
        }

        address_complement.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 5)
            if (error) setError(true, address_complement, "mínimo 5 caracteres")
            else setError(false, address_complement, null)
        }

        address_postal_address.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 8)
            if (error) setError(true, address_postal_address, "mínimo 8 caracteres")
            else setError(false, address_postal_address, null)
        }

        address_state.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 5)
            if (error) setError(true, address_state, "mínimo 5 caracteres")
            else setError(false, address_state, null)
        }

        address_city.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 5)
            if (error) setError(true, address_city, "mínimo 5 caracteres")
            else setError(false, address_city, null)
        }

        address_latitude.editText?.doAfterTextChanged { text ->
            val error = viewModel.validLatitudeAndLongitude(text.toString())
            if (!error) setError(true, address_latitude, "O valor precisa estar entre -90 e 90")
            else setError(false, address_latitude, null)
        }

        address_longitude.editText?.doAfterTextChanged { text ->
            val error = viewModel.validLatitudeAndLongitude(text.toString())
            if (!error) setError(true, address_longitude, "O valor precisa estar entre -90 e 90")
            else setError(false, address_longitude, null)
        }



    }

    fun sendRegistrationData(view: View) {
        val latitude = address_latitude.editText?.text?.toString()
        val longitude = address_latitude.editText?.text?.toString()

        val latitudeDouble = viewModel.toDouble(latitude)
        val longitudeDouble = viewModel.toDouble(longitude)

        registrationData?.let {registrationData ->
            registrationData.rua = address_street_name.editText?.text.toString()
            registrationData.numero = address_street_number.editText?.text.toString()
            registrationData.complemento = address_complement.editText?.text.toString()
            registrationData.cep = address_postal_address.editText?.text.toString()
            registrationData.estado = address_state.editText?.text.toString()
            registrationData.cidade = address_city.editText?.text.toString()
            registrationData.latitude = latitudeDouble
            registrationData.longitude = longitudeDouble

            val validAllFields = viewModel.validAllFields(registrationData)

            if (validAllFields) {
                viewModel.salveInFirebase(registrationData)
                viewModel.save.observe(this, Observer {
                    if(it) {
                        Toast.makeText(this, "salvo", Toast.LENGTH_LONG).show()
                        viewModel.getRegistrationUser()
                        viewModel.remoteRegistration.observe(this, Observer { result ->
                            if(result != null ){
                                val intent = Intent(this, HomeActivity::class.java)
                                val registrationData = RegistrationData(
                                    result.nome!!,
                                    result.sobrenome!!,
                                    result.email!!,
                                    result.telefone!!,
                                    result.dataAniversario!!,
                                    result.rua!!,
                                    result.numero!!,
                                    result.complemento!!,
                                    result.cep!!,
                                    result.estado!!,
                                    result.cidade!!,
                                    result.coordenadas!!.latitude!!,
                                    result.coordenadas!!.longitude!!
                                )
                                intent.putExtra("EXTRA_REGISTRATION", registrationData)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "ocorreu um erro", Toast.LENGTH_LONG).show()
                            }
                        })
                    } else {
                        Toast.makeText(this, "ocorreu um erro", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "preencha todos os campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupToolbar() {
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }
}