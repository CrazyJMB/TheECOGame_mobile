package com.dds.theecogame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.databinding.ActivityParticipantProfileBinding
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.domain.userRestrictions.UserRestrictions
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.register.viewModel.RegisterViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ActivityParticipantProfile : AppCompatActivity() {

    private lateinit var binding: ActivityParticipantProfileBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var restrictions: UserRestrictions

    private val userRepository: UserRepository = UserRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipantProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TODO cargar datos
        GlobalScope.launch {
            //val user = userRepository.getUser(//email)
            //binding.etUsername.text = user.username.toString()
            //binding.etPassword.text= user.password.toString()
            //binding.etEmail.text = user.email.toString()
        }

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
            binding.bnSave.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && restrictions.checkUsername(binding.etUsername.text.toString())
                    && restrictions.checkEmail(binding.etEmail.text.toString())
                    && restrictions.checkPassword(binding.etPassword.text.toString()))
        }
        binding.etEmail.addTextChangedListener {
            binding.bnSave.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && restrictions.checkUsername(binding.etUsername.text.toString())
                    && restrictions.checkEmail(binding.etEmail.text.toString())
                    && restrictions.checkPassword(binding.etPassword.text.toString()))
        }
        binding.etPassword.addTextChangedListener {
            binding.bnSave.isEnabled = (!binding.etUsername.text.toString().isEmpty()
                    && !binding.etEmail.text.toString().isEmpty()
                    && !binding.etPassword.text.toString().isEmpty()
                    && restrictions.checkUsername(binding.etUsername.text.toString())
                    && restrictions.checkEmail(binding.etEmail.text.toString())
                    && restrictions.checkPassword(binding.etPassword.text.toString()))
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainScreenActivity::class.java)
            startActivity(intent)
        }

        binding.bnSave.setOnClickListener {
            GlobalScope.launch {
                //TODO actualiza los datos
                //userRepository.updateUser()
            }

            val intent = Intent(this, MainScreenActivity::class.java)
            startActivity(intent)
        }
    }

}