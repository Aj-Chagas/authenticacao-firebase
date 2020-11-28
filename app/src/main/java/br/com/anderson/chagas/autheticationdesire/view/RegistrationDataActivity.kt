package br.com.anderson.chagas.autheticationdesire.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import br.com.anderson.chagas.autheticationdesire.R
import br.com.anderson.chagas.autheticationdesire.helpers.setError
import br.com.anderson.chagas.autheticationdesire.model.Address
import br.com.anderson.chagas.autheticationdesire.model.RegistrationData
import br.com.anderson.chagas.autheticationdesire.viewmodel.RegistrationDataViewModel
import kotlinx.android.synthetic.main.activity_registration_data.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegistrationDataActivity : AppCompatActivity() {

    val viewModel: RegistrationDataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_data)

        // viewModel.salveInFirebase()
        registration_data_first_name.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 5)
            if (error) setError(true, registration_data_first_name, "mínimo 5 caracteres") else setError(false, registration_data_first_name, null)
        }

        registration_data_last_name.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 5)
            if (error) setError(true, registration_data_last_name, "mínimo 5 caracteres") else setError(false, registration_data_last_name, null)
        }

        registration_data_email.editText?.doAfterTextChanged { text ->
            val error = viewModel.validEmail(text.toString())
            if (error) setError(true, registration_data_email, "email inválido") else setError(false, registration_data_email, null)
        }

        registration_data_phone_number.editText?.doAfterTextChanged { text ->
            val error = viewModel.validationPhoneNumber(text.toString())
            if (error) {
                setError(true, registration_data_phone_number, "número de telefone inválido")
            } else{
                setError(false, registration_data_phone_number, null)
            }
        }


    }

    fun nextButtonOnClick(view: View) {
        val registrationData = RegistrationData(
            firstName = registration_data_first_name.editText?.text.toString(),
            lastName = registration_data_last_name.editText?.text.toString(),
            email = registration_data_email.editText?.text.toString(),
            phone = registration_data_phone_number.editText?.text.toString(),
            birthDate = registration_data_birth_date.editText?.text.toString(),
            address = Address(
                streetName = "",
                numberHouse = "",
                complement = "",
                postalCode = "",
                state = "",
                city = "",
                latitude = 0.0,
                longitude = 0.0
            )
        )

        val validAllField = viewModel.validRegistrationFields(registrationData)

        if (validAllField) {
            val intent = Intent(this, AddressActivity::class.java)
            intent.putExtra("EXTRA_REGISTRATION", registrationData);
            startActivity(intent)
        } else {
            Toast.makeText(this, "preencha tudo porra", Toast.LENGTH_LONG).show()
        }
    }
}