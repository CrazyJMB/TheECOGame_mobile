package com.dds.theecogame.presentation.auth.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.domain.Application
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.databinding.ActivityLogInBinding
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.presentation.auth.login.viewModel.LoginActivityViewModel
import com.dds.theecogame.presentation.auth.userManagement.view.UserManagementActivity
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding

    private lateinit var viewModel: LoginActivityViewModel
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository = UserRepositoryImpl()
        viewModel = LoginActivityViewModel(userRepository)


        binding.btnLogin.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val email = binding.inputEmail.text.toString()
                val password = binding.inputPassword.text.toString()

                viewModel.checkPassword(email, password).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            viewModel.login(email).collect { user ->
                                when (user) {
                                    is Resource.Loading -> {}
                                    is Resource.Success -> {
                                        Application.setUser(user.data!!)
                                        withContext(Dispatchers.Main) {
                                            goToMainScreen()
                                        }
                                    }

                                    is Resource.Error -> {
                                        withContext(Dispatchers.Main) {
                                            Toast.makeText(
                                                this@LoginActivity,
                                                R.string.error_msg,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            }
                        }

                        is Resource.Error -> {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    R.string.error_msg_emailOrPassword,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
        binding.ibBack.setOnClickListener {
            startActivity(Intent(this, UserManagementActivity::class.java))
        }
    }

    fun goToMainScreen() {
        startActivity(Intent(this@LoginActivity, MainScreenActivity::class.java))
    }
}