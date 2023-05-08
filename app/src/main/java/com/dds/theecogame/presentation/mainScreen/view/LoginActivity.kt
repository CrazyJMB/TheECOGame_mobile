package com.dds.theecogame.presentation.mainScreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.common.Application
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.databinding.ActivityLogInBinding
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.presentation.mainScreen.viewModel.MainScreenViewModel
import com.dds.theecogame.presentation.userManagement.view.UserManagementActivity
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    private val viewModel: MainScreenViewModel by viewModels()
    private val userRepository: UserRepository = UserRepositoryImpl()

    private var userIsCorrect = false
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
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
         */

        binding.btnLogin.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                userRepository.checkPassword(
                    binding.inputEmail.text.toString(),
                    binding.inputPassword.text.toString()
                ).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            //TODO: Crear objeto User común
                            lifecycleScope.launch(Dispatchers.IO) {
                                userRepository.getUser(binding.inputEmail.text.toString())
                                    .collect { user ->
                                        when (user) {
                                            is Resource.Loading -> {}
                                            is Resource.Success -> {
                                                Application.setUser(user.data!!)
                                                //GlobalScope.launch { goToMainScreen() }
                                            }
                                            is Resource.Error -> {
                                                //TODO: Popup error
                                            }
                                        }
                                    }

                            }
                        }
                        is Resource.Error -> {
                        }
                    }
                }
            }
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = Application.getUser().email
        }
        binding.ibBack.setOnClickListener {
            goToUserManagement()
        }
    }

    fun goToMainScreen() {
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
    }

    private fun goToUserManagement() {
        val intent = Intent(this, UserManagementActivity::class.java)
        startActivity(intent)
    }
}