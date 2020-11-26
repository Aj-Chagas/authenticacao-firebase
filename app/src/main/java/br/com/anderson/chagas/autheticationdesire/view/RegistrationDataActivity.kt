package br.com.anderson.chagas.autheticationdesire.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.anderson.chagas.autheticationdesire.R
import br.com.anderson.chagas.autheticationdesire.viewmodel.LoginViewModel
import br.com.anderson.chagas.autheticationdesire.viewmodel.RegistrationDataViewModel
import kotlinx.android.synthetic.main.activity_singup.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegistrationDataActivity : AppCompatActivity() {

    val viewModel: RegistrationDataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        viewModel.salveInFirebase()

    }
}