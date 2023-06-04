package com.dds.theecogame.presentation.auth.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.databinding.ActivityRegisterBinding
import com.dds.theecogame.domain.factory.ValidatorFactory
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.presentation.auth.register.viewModel.RegisterViewModel
import com.dds.theecogame.presentation.auth.userManagement.view.UserManagementActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var viewModel: RegisterViewModel
    private lateinit var userRepository: UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository = UserRepositoryImpl()
        viewModel = RegisterViewModel(userRepository)

        addErrorsListeners()

        addActivationCreateUserListener()
        addCreateUserListener()

        binding.ibBack.setOnClickListener {
            goToUserManagement()
        }

    }

    private fun addActivationCreateUserListener() {
        binding.etUsername.addTextChangedListener {
            textChangeListenerCondition()
        }
        binding.etEmail.addTextChangedListener {
            textChangeListenerCondition()
        }
        binding.etPassword.addTextChangedListener {
            textChangeListenerCondition()
        }
    }


    private fun textChangeListenerCondition() {
        val username = binding.etUsername.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        binding.btnCreateUser.isEnabled = (
                username.isNotEmpty()
                        && email.isNotEmpty()
                        && password.isNotEmpty()
                        && viewModel.checkUsername(username)
                        && viewModel.checkEmail(email)
                        && viewModel.checkPassword(password)
                )
    }

    private fun addCreateUserListener() {
        binding.btnCreateUser.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.createUser(
                    username,
                    "name",
                    "surname",
                    email,
                    password
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
    }

    private fun addErrorsListeners() {
        binding.etUsername.addTextChangedListener { text: Editable? ->
            text.let {
                if (viewModel.checkUsername(it.toString())) {
                    binding.tvUsernameError.visibility = View.INVISIBLE
                } else {
                    binding.tvUsernameError.visibility = View.VISIBLE
                    binding.tvUsernameError.text = viewModel.getUsernameError()
                }
            }
        }

        binding.etEmail.addTextChangedListener { text: Editable? ->
            text.let {
                if (viewModel.checkEmail(it.toString())) {
                    binding.tvEmailError.visibility = View.INVISIBLE
                } else {
                    binding.tvEmailError.visibility = View.VISIBLE
                    binding.tvEmailError.text = viewModel.getEmailError()
                }
            }
        }

        binding.etPassword.addTextChangedListener { text: Editable? ->
            text.let {
                if (viewModel.checkPassword(it.toString())) {
                    binding.tvPasswordError.visibility = View.INVISIBLE
                } else {
                    binding.tvPasswordError.visibility = View.VISIBLE
                    binding.tvPasswordError.text = viewModel.getPasswordError()
                }
            }
        }
    }

    private fun goToUserManagement() {
        val intent = Intent(this, UserManagementActivity::class.java)
        startActivity(intent)
    }
}