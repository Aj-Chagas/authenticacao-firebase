package br.com.anderson.chagas.autheticationdesire.helpers

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

 fun AppCompatActivity.setError(
    isError: Boolean,
    textInputLayout: TextInputLayout,
    msg: String?
) {
    when (isError) {
        true -> textInputLayout.error = msg
        false -> textInputLayout.error = null
    }
}