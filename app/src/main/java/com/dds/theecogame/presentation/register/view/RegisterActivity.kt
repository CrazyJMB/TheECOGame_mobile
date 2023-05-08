package com.dds.theecogame.presentation.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.presentation.register.viewModel.RegisterViewModel
import com.dds.theecogame.databinding.ActivityRegisterBinding
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.domain.userRestrictions.UserRestrictions
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var restrictions: UserRestrictions

    private val userRepository: UserRepository = UserRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        restrictions = UserRestrictions(this)
        setContentView(binding.root)

        binding.etUsername.setOnFocusChangeListener { view, b ->
            if (!b) {
                var check = restrictions.checkUsername(binding.etUsername.text.toString())
                if (!check) {
                    binding.tvUsernameError.visibility = View.VISIBLE
                    binding.tvUsernameError.text = restrictions.getError()
                } else {
                    binding.tvUsernameError.visibility = View.INVISIBLE
                }
            }
        }

        binding.etEmail.setOnFocusChangeListener { view, b ->
            if (!b) {
                var check = restrictions.checkEmail(binding.etEmail.text.toString())
                if (!check) {
                    binding.tvEmailError.visibility = View.VISIBLE
                    binding.tvEmailError.text = restrictions.getError()
                } else {
                    binding.tvEmailError.visibility = View.INVISIBLE
                }
            }
        }

        binding.etPassword.setOnFocusChangeListener { view, b ->
            if (!b) {
                var check = restrictions.checkPassword(binding.etPassword.text.toString())
                if (!check) {
                    binding.tvPasswordError.visibility = View.VISIBLE
                    binding.tvPasswordError.text = restrictions.getError()
                } else {
                    binding.tvPasswordError.visibility = View.INVISIBLE
                }
            }
        }

        binding.etUsername.addTextChangedListener {
            binding.btnCreateUser.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && restrictions.checkUsername(binding.etUsername.text.toString())
                    && restrictions.checkEmail(binding.etEmail.text.toString())
                    && restrictions.checkPassword(binding.etPassword.text.toString()))
        }
        binding.etEmail.addTextChangedListener {
            binding.btnCreateUser.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && restrictions.checkUsername(binding.etUsername.text.toString())
                    && restrictions.checkEmail(binding.etEmail.text.toString())
                    && restrictions.checkPassword(binding.etPassword.text.toString()))
        }
        binding.etPassword.addTextChangedListener {
            binding.btnCreateUser.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && restrictions.checkUsername(binding.etUsername.text.toString())
                    && restrictions.checkEmail(binding.etEmail.text.toString())
                    && restrictions.checkPassword(binding.etPassword.text.toString()))
        }

        binding.btnCreateUser.setOnClickListener {
            //TODO guardar el usuario creado en el datastore
            GlobalScope.launch {
                userRepository.createUser(
                    username = binding.etUsername.toString(),
                    password = binding.etPassword.toString(),
                    email = binding.etEmail.toString(),
                    name = "PABLO",
                    surname = "ESCOBAR"
                )
            }

            val intent = Intent(this, MainScreenActivity::class.java)
            startActivity(intent)
        }
    }
}