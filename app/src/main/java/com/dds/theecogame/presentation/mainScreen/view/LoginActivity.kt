package com.dds.theecogame.presentation.mainScreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.data.local.DataStoreManager
import com.dds.theecogame.dataStore
import com.dds.theecogame.databinding.ActivityLogInBinding
import com.dds.theecogame.presentation.game.view.GameActivity
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.mainScreen.viewModel.MainScreenViewModel
import com.dds.theecogame.presentation.userManagement.view.UserManagementActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    private val viewModel: MainScreenViewModel by viewModels()

    private var userIsCorrect = false
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            email = binding.inputEmail.toString()
            password = binding.inputPassword.toString()

            GlobalScope.launch {
                val dataStoreManager = DataStoreManager(dataStore = dataStore)
                userIsCorrect = viewModel.login(email, password, dataStoreManager)
            }

            if (userIsCorrect) {
                goToMainScreen()
            } else {
                binding.tvError.visibility = View.VISIBLE
            }
        }

        binding.ibBack.setOnClickListener {
            goToUserManagement()
        }

    }

    private fun goToMainScreen() {
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
    }

    private fun goToUserManagement() {
        val intent = Intent(this, UserManagementActivity::class.java)
        startActivity(intent)
    }
}