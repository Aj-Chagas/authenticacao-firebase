package br.com.anderson.chagas.autheticationdesire.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.anderson.chagas.autheticationdesire.R
import br.com.anderson.chagas.autheticationdesire.model.User
import br.com.anderson.chagas.autheticationdesire.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_singup.*
import org.koin.android.viewmodel.ext.android.viewModel

class SingupActivity : AppCompatActivity() {

    val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)
        title = "Cadastro"

        viewModel.loginliveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            finish()
        })
        btnClickListener()
    }

    private fun btnClickListener() {
        bt_signup.setOnClickListener {
            val email = txt_email.editText?.text.toString()
            val password  =  txt_senha.editText?.text.toString()
            val user = User(email, password)
            viewModel.singup(user)
        }
    }

    private fun setError(
        isError: Boolean,
        textInputLayout: TextInputLayout,
        msg: String
    ) {
        when (isError) {
            true -> textInputLayout.error = msg
            false -> textInputLayout.error = null
        }
    }

}