package br.com.anderson.chagas.autheticationdesire.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.anderson.chagas.autheticationdesire.R
import br.com.anderson.chagas.autheticationdesire.model.RegistrationData
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.item_data.view.*
import kotlinx.android.synthetic.main.item_title.view.*

class HomeActivity : AppCompatActivity() {

    private var registrationData: RegistrationData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        registrationData = intent.getSerializableExtra("EXTRA_REGISTRATION") as RegistrationData

        item_title_1.item_title_title.text = "Dados pessoais"

        item_data_name.item_data_name_label.text = "Nome"
        item_data_name.item_data_id_name.text = registrationData?.nome

        item_data_last_name.item_data_name_label.text = "Sobrenome"
        item_data_last_name.item_data_id_name.text = registrationData?.sobrenome

        item_data_email.item_data_name_label.text = "Email"
        item_data_email.item_data_id_name.text = registrationData?.email

        item_data_phone.item_data_name_label.text = "Telefone"
        item_data_phone.item_data_id_name.text = registrationData?.telefone

        item_data_birth_date.item_data_name_label.text = "Data aniversário"
        item_data_birth_date.item_data_id_name.text = registrationData?.dataAniversario

        item_title_2.item_title_title.text = "Endereço"

        item_data_street.item_data_name_label.text = "Rua/Avenida"
        item_data_street.item_data_id_name.text = registrationData?.rua

        item_data_complement.item_data_name_label.text = "Complemento"
        item_data_complement.item_data_id_name.text = registrationData?.complemento

        item_data_cep.item_data_name_label.text = "Cep"
        item_data_cep.item_data_id_name.text = registrationData?.cep

        item_data_state.item_data_name_label.text = "Estado"
        item_data_state.item_data_id_name.text = registrationData?.estado

        item_data_city.item_data_name_label.text = "Cidade"
        item_data_city.item_data_id_name.text = registrationData?.cidade

        item_data_latitude.item_data_name_label.text = "latitude"
        item_data_latitude.item_data_id_name.text = registrationData?.latitude.toString()

        item_data_longitude.item_data_name_label.text = "longitude"
        item_data_longitude.item_data_id_name.text = registrationData?.latitude.toString()

    }

    fun onClickEdit(view: View) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("EXTRA_REGISTRATION", registrationData)
        startActivity(intent)
    }
}