package br.com.anderson.chagas.autheticationdesire.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel.loginliveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            finish()
        })
        btnClickListener()
    }

    private fun btnClickListener() {
        bt_login.setOnClickListener {
            val name = txt_nome.editText?.text.toString()
            val email = txt_email.editText?.text.toString()
            val password  =  txt_senha.editText?.text.toString()
            val user = User(name, email, password)
            viewModel.singup(user)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
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

    override fun onBackPressed() {
        super.onBackPressed()
        true
    }
}