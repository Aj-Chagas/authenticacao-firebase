package br.com.anderson.chagas.autheticationdesire.view

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.anderson.chagas.autheticationdesire.R
import kotlinx.android.synthetic.main.activity_sucess.*

class SucessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sucess)

        val msg = intent.getStringExtra("msg")
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

        animationView.addAnimatorListener(object :
            AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                // Do nothing
            }

            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SucessActivity, SingupActivity::class.java))
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {
                // Do nothing
            }

            override fun onAnimationRepeat(animation: Animator) {
                // Do nothing
            }
        })
    }
}