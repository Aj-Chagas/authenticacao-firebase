package br.com.anderson.chagas.autheticationdesire.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.anderson.chagas.autheticationdesire.R
import br.com.anderson.chagas.autheticationdesire.model.User
import br.com.anderson.chagas.autheticationdesire.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_singup.*
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.core.widget.doAfterTextChanged
import br.com.anderson.chagas.autheticationdesire.helpers.invisible
import br.com.anderson.chagas.autheticationdesire.helpers.setError
import br.com.anderson.chagas.autheticationdesire.helpers.visible
import br.com.anderson.chagas.autheticationdesire.model.RegistrationData

class SingupActivity : AppCompatActivity() {

    val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)
        title = "Cadastro"

        txt_email.editText?.doAfterTextChanged { text ->
            val error = viewModel.validEmail(text.toString())
            if (error) setError(true, txt_email, "email inválido") else setError(false, txt_email, null)
        }

        txt_senha.editText?.doAfterTextChanged { text ->
            val error = viewModel.validPassword(text.toString())
            if (error) setError(true, txt_senha, "mínimo 6 dígitos") else setError(false, txt_senha, null)
        }

        viewModel.loginliveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            bt_signin.visible()
            bt_signup.visible()
        })

        viewModel.sucessLogin.observe(this, Observer {
            viewModel.getRegistrationUser()
        })

        viewModel.remoteRegistration.observe(this, Observer { result ->
            if (result != null && result.status == true) {
                val intent = Intent(this, HomeActivity::class.java)
                // intent.putExtra("msg", it)
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
                val intent = Intent(this, RegistrationDataActivity::class.java)
                // intent.putExtra("msg", it)
                startActivity(intent)
                finish()
            }
        })

        btnSignupClickListener()
        btnSigninClickListner()
    }

    private fun btnSigninClickListner() {

        bt_signin.setOnClickListener {
            val email = txt_email.editText?.text.toString()
            val password = txt_senha.editText?.text.toString()
            val user = User(email, password)
            viewModel.signin(user)
            bt_signin.invisible()
        }
    }


    private fun btnSignupClickListener() {
        bt_signup.setOnClickListener {
            val email = txt_email.editText?.text.toString()
            val password  =  txt_senha.editText?.text.toString()
            val user = User(email, password)
            viewModel.singup(user)
            bt_signup.invisible()
        }
    }

}