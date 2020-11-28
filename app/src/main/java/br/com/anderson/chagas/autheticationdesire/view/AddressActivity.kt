package br.com.anderson.chagas.autheticationdesire.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import br.com.anderson.chagas.autheticationdesire.R
import br.com.anderson.chagas.autheticationdesire.helpers.setError
import br.com.anderson.chagas.autheticationdesire.model.RegistrationData
import br.com.anderson.chagas.autheticationdesire.viewmodel.RegistrationDataViewModel
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_registration_data.*
import kotlinx.android.synthetic.main.activity_singup.*
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
            val error = viewModel.minimumCharacterValidation(text.toString(), 1)
            if (error) setError(true, address_latitude, "mínimo 1 caracteres")
            else setError(false, address_latitude, null)
        }

        address_longitude.editText?.doAfterTextChanged { text ->
            val error = viewModel.minimumCharacterValidation(text.toString(), 1)
            if (error) setError(true, address_longitude, "mínimo 1 caracteres")
            else setError(false, address_longitude, null)
        }



    }

    fun sendRegistrationData(view: View) {
        registrationData?.let {registrationData ->
            registrationData.address?.streetName = address_street_name.editText?.text.toString()
            registrationData.address?.numberHouse = address_street_number.editText?.text.toString()
            registrationData.address?.complement = address_complement.editText?.text.toString()
            registrationData.address?.postalCode = address_postal_address.editText?.text.toString()
            registrationData.address?.state = address_state.editText?.text.toString()
            registrationData.address?.city = address_city.editText?.text.toString()
            registrationData.address?.latitude = address_latitude.editText?.text.toString().toDouble()
            registrationData.address?.longitude = address_longitude.editText?.text.toString().toDouble()

            val validAllFields = viewModel.validAllFields(registrationData)

            if (validAllFields) {
                Toast.makeText(this, "pronto para salvar porra", Toast.LENGTH_LONG).show()
                viewModel.salveInFirebase(registrationData)
            } else {
                Toast.makeText(this, "preencha tudo caraolhooo", Toast.LENGTH_LONG).show()
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