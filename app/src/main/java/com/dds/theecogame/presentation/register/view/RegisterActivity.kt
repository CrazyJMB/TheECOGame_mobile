package com.dds.theecogame.presentation.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.dds.theecogame.presentation.register.viewModel.RegisterViewModel
import com.dds.theecogame.databinding.ActivityRegisterBinding
import com.dds.theecogame.domain.userRestrictions.UserRestrictions

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var restrictions: UserRestrictions

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

    }
}