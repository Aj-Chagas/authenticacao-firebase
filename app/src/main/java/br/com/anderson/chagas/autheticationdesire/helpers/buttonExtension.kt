package br.com.anderson.chagas.autheticationdesire.helpers

import android.view.View
import android.widget.Button

fun Button.invisible() {
    this.visibility = View.INVISIBLE
}

fun Button.visible() {
    this.visibility = View.VISIBLE
}