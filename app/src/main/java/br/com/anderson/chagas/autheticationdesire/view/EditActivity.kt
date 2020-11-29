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
import kotlinx.android.synthetic.main.activity_edit.*
import org.koin.android.viewmodel.ext.android.viewModel

class EditActivity : AppCompatActivity() {

    val viewModel: RegistrationDataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val registrationData = intent.getSerializableExtra("EXTRA_REGISTRATION") as RegistrationData

        setupToolbar()

        bindView(registrationData)

        registration_first_name.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 3)
            if (error) setError(true, registration_first_name, "mínimo 3 caracteres") else setError(false, registration_first_name, null)
        }

        registration_last_name.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 3)
            if (error) setError(true, registration_last_name, "mínimo 3 caracteres") else setError(false, registration_last_name, null)
        }

        registration_email.editText?.doAfterTextChanged { text ->
            val error = viewModel.validEmail(text.toString())
            if (error) setError(true, registration_email, "email inválido") else setError(false, registration_email, null)
        }

        registration_phone.editText?.doAfterTextChanged { text ->
            val error = viewModel.validationPhoneNumber(text.toString())
            if (error) setError(true, registration_phone, "número de telefone inválido") else setError(false, registration_phone, null)
        }

        registration_birth_date.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 10)
            if (error) setError(true, registration_birth_date, "data de aniversario invalida. ex: dd/mm/aaaa") else setError(false, registration_birth_date, null)
        }



        registratio_street.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 3)
            if (error) setError(true, registratio_street, "mínimo 3 caracteres")
            else setError(false, registratio_street, null)
        }

        registratio_number.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 1)
            if (error) setError(true, registratio_number, "mínimo 1 caracteres")
            else setError(false, registratio_number, null)
        }

        registratio_complemento.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 2)
            if (error) setError(true, registratio_complemento, "mínimo 2 caracteres")
            else setError(false, registratio_complemento, null)
        }

        registratio_cep.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 8)
            if (error) setError(true, registratio_cep, "mínimo 8 caracteres")
            else setError(false, registratio_cep, null)
        }

        registratio_estado.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 2)
            if (error) setError(true, registratio_estado, "mínimo 2 caracteres")
            else setError(false, registratio_estado, null)
        }

        registratio_cidade.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 2)
            if (error) setError(true, registratio_cidade, "mínimo 2 caracteres")
            else setError(false, registratio_cidade, null)
        }

        registratio_latitude.editText?.doAfterTextChanged { text ->
            val error = viewModel.validLatitudeAndLongitude(text.toString())
            if (!error) setError(true, registratio_latitude, "O valor precisa estar entre -90 e 90")
            else setError(false, registratio_latitude, null)
        }

        registratio_longitude.editText?.doAfterTextChanged { text ->
            val error = viewModel.validLatitudeAndLongitude(text.toString())
            if (!error) setError(true, registratio_longitude, "O valor precisa estar entre -90 e 90")
            else setError(false, registratio_longitude, null)
        }


    }

    private fun bindView(registrationData: RegistrationData) {
        registration_first_name.editText?.setText(registrationData.nome)
        registration_last_name.editText?.setText(registrationData.sobrenome)
        registration_email.editText?.setText(registrationData.email)
        registration_phone.editText?.setText(registrationData.telefone)
        registration_birth_date.editText?.setText(registrationData.dataAniversario)


        registratio_street.editText?.setText(registrationData.rua)
        registratio_number.editText?.setText(registrationData.numero)
        registratio_complemento.editText?.setText(registrationData.complemento)
        registratio_cep.editText?.setText(registrationData.cep)
        registratio_estado.editText?.setText(registrationData.estado)
        registratio_cidade.editText?.setText(registrationData.cidade)
        registratio_latitude.editText?.setText(registrationData.latitude.toString())
        registratio_longitude.editText?.setText(registrationData.longitude.toString())
    }

    fun onClickSave(view: View) {

        val latitude = registratio_latitude.editText?.text?.toString()
        val longitude = registratio_longitude.editText?.text?.toString()

        val latitudeDouble = viewModel.toDouble(latitude)
        val longitudeDouble = viewModel.toDouble(longitude)

        val registration = RegistrationData(
            nome = registration_first_name.editText?.text?.toString() ?: "",
            sobrenome = registration_last_name.editText?.text?.toString() ?: "",
            email = registration_email.editText?.text?.toString() ?: "",
            telefone = registration_phone.editText?.text?.toString() ?: "",
            dataAniversario = registration_birth_date.editText?.text?.toString() ?: "",
            rua = registratio_street.editText?.text?.toString() ?: "",
            numero = registratio_number.editText?.text?.toString() ?: "",
            complemento = registratio_complemento.editText?.text?.toString() ?: "",
            cep = registratio_cep.editText?.text?.toString() ?: "",
            estado = registratio_estado.editText?.text?.toString() ?: "",
            cidade = registratio_cidade.editText?.text?.toString() ?: "",
            latitude = latitudeDouble,
            longitude = longitudeDouble
        )
        val validAllFields = viewModel.validAllFields(registration)

        if (validAllFields) {
            viewModel.salveInFirebase(registration)
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
                            finish()
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

    fun onClickDelete(view: View) {
        viewModel.disableAccount()
        viewModel.disable.observe(this, Observer {
            if(it) {
                Toast.makeText(this, "dados apagados", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, SingupActivity::class.java))
            } else {
                Toast.makeText(this, "ocorreu um erro", Toast.LENGTH_SHORT).show()
            }
        })
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