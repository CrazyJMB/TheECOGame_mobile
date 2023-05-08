package com.dds.theecogame.presentation.editProfile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivityEditProfileBinding
import com.dds.theecogame.domain.userRestrictions.UserRestrictions
import com.dds.theecogame.presentation.userManagement.viewModel.EditProfileViewModel

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private val viewModel: EditProfileViewModel by viewModels()
    private lateinit var restrictions: UserRestrictions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
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
            binding.btnSave.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && restrictions.checkUsername(binding.etUsername.text.toString())
                    && restrictions.checkEmail(binding.etEmail.text.toString())
                    && restrictions.checkPassword(binding.etPassword.text.toString()))
        }
        binding.etEmail.addTextChangedListener {
            binding.btnSave.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && restrictions.checkUsername(binding.etUsername.text.toString())
                    && restrictions.checkEmail(binding.etEmail.text.toString())
                    && restrictions.checkPassword(binding.etPassword.text.toString()))
        }
        binding.etPassword.addTextChangedListener {
            binding.btnSave.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && restrictions.checkUsername(binding.etUsername.text.toString())
                    && restrictions.checkEmail(binding.etEmail.text.toString())
                    && restrictions.checkPassword(binding.etPassword.text.toString()))
        }

    }
}