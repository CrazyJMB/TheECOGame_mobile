package com.dds.theecogame.presentation.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.databinding.ActivityRegisterBinding
import com.dds.theecogame.domain.factory.ValidatorFactory
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.presentation.userManagement.view.UserManagementActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val userRepository: UserRepository = UserRepositoryImpl()

    private val usernameValidator = ValidatorFactory.getValidator("username")
    private val emailValidator = ValidatorFactory.getValidator("email")
    private val passwordValidator = ValidatorFactory.getValidator("password")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etUsername.setOnFocusChangeListener { view, b ->
            if (!b) {
                var check = usernameValidator.validate(binding.etUsername.text.toString())
                if (!check) {
                    binding.tvUsernameError.visibility = View.VISIBLE
                    binding.tvUsernameError.text = usernameValidator.getError()
                } else {
                    binding.tvUsernameError.visibility = View.INVISIBLE
                }
            }
        }

        binding.etEmail.setOnFocusChangeListener { view, b ->
            if (!b) {
                var check = emailValidator.validate(binding.etEmail.text.toString())
                if (!check) {
                    binding.tvEmailError.visibility = View.VISIBLE
                    binding.tvEmailError.text = emailValidator.getError()
                } else {
                    binding.tvEmailError.visibility = View.INVISIBLE
                }
            }
        }

        binding.etPassword.setOnFocusChangeListener { view, b ->
            if (!b) {
                var check = passwordValidator.validate(binding.etPassword.text.toString())
                if (!check) {
                    binding.tvPasswordError.visibility = View.VISIBLE
                    binding.tvPasswordError.text = passwordValidator.getError()
                } else {
                    binding.tvPasswordError.visibility = View.INVISIBLE
                }
            }
        }

        binding.etUsername.addTextChangedListener {
            binding.btnCreateUser.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && usernameValidator.validate(binding.etUsername.text.toString())
                    && emailValidator.validate(binding.etEmail.text.toString())
                    && passwordValidator.validate(binding.etPassword.text.toString()))
        }
        binding.etEmail.addTextChangedListener {
            binding.btnCreateUser.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && usernameValidator.validate(binding.etUsername.text.toString())
                    && emailValidator.validate(binding.etEmail.text.toString())
                    && passwordValidator.validate(binding.etPassword.text.toString()))
        }
        binding.etPassword.addTextChangedListener {
            binding.btnCreateUser.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && usernameValidator.validate(binding.etUsername.text.toString())
                    && emailValidator.validate(binding.etEmail.text.toString())
                    && passwordValidator.validate(binding.etPassword.text.toString()))
        }

        binding.btnCreateUser.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                userRepository.createUser(
                    binding.etUsername.text.toString(),
                    "name",
                    "surname",
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                ).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            withContext(Dispatchers.Main) { goToUserManagement() }
                        }

                        is Resource.Error -> {}
                    }
                }
            }
        }

        binding.ibBack.setOnClickListener {
            goToUserManagement()
        }

    }

    private fun goToUserManagement() {
        val intent = Intent(this, UserManagementActivity::class.java)
        startActivity(intent)
    }
}