package com.dds.theecogame.presentation.mainScreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dds.theecogame.databinding.ActivityLogInBinding
import com.dds.theecogame.presentation.game.view.GameActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            email = binding.inputEmail.toString()
            password = binding.inputPassword.toString()

            //TODO comprobar si es correcto y si lo es iniciar sesion
            //binding.tvError.visibility = View.VISIBLE
        }
    }

    fun goToMainScreen (){
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
    }
}