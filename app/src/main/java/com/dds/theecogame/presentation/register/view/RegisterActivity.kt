package com.dds.theecogame.presentation.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.presentation.game.viewModel.GameViewModel
import com.dds.theecogame.presentation.register.viewModel.RegisterViewModel
import androidx.fragment.app.activityViewModels
import com.dds.theecogame.databinding.ActivityMainScreenBinding
import com.dds.theecogame.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etUsername.setOnFocusChangeListener { view, b ->
            if (!b) {
                var error = viewModel.checkUsername(binding.etUsername.text.toString())
                if (!error.isNullOrEmpty()) {
                    binding.tvUsernameError.visibility = View.VISIBLE
                    binding.tvUsernameError.text = error
                } else {
                    binding.tvUsernameError.visibility = View.INVISIBLE
                }
            }
        }

        binding.etEmail.setOnFocusChangeListener { view, b ->
            if (!b) {
                var error = viewModel.checkEmail(binding.etEmail.text.toString())
                if (!error.isNullOrEmpty()) {
                    binding.tvEmailError.visibility = View.VISIBLE
                    binding.tvEmailError.text = error
                } else {
                    binding.tvEmailError.visibility = View.INVISIBLE
                }
            }
        }

        binding.etPassword.setOnFocusChangeListener { view, b ->
            if (!b) {
                var error = viewModel.checkPassword(binding.etPassword.text.toString())
                if (!error.isNullOrEmpty()) {
                    binding.tvPasswordError.visibility = View.VISIBLE
                    binding.tvPasswordError.text = error
                } else {
                    binding.tvPasswordError.visibility = View.INVISIBLE
                }
            }
        }

    }
}