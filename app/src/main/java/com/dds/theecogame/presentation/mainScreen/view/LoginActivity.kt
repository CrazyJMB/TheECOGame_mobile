package com.dds.theecogame.presentation.mainScreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.R
import com.dds.theecogame.domain.Application
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.databinding.ActivityLogInBinding
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.presentation.mainScreen.viewModel.MainScreenViewModel
import com.dds.theecogame.presentation.userManagement.view.UserManagementActivity
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding

    //private val viewModel: MainScreenViewModel by viewModels()
    private val userRepository: UserRepository = UserRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                userRepository.checkPassword(
                    binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString()
                ).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            lifecycleScope.launch(Dispatchers.IO) {
                                userRepository.getUser(binding.inputEmail.text.toString())
                                    .collect { user ->
                                        when (user) {
                                            is Resource.Loading -> {}
                                            is Resource.Success -> {
                                                Application.setUser(user.data!!)
                                                withContext(Dispatchers.Main) {
                                                    startActivity(
                                                        Intent(
                                                            this@LoginActivity,
                                                            MainScreenActivity::class.java
                                                        )
                                                    )
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
}