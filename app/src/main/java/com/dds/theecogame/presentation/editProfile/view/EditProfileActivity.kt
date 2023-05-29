package com.dds.theecogame.presentation.editProfile.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivityEditProfileBinding
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.factory.ValidatorFactory
import com.dds.theecogame.domain.model.User
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.editProfile.viewModel.EditProfileViewModel
import com.squareup.picasso.Picasso
import java.lang.Exception

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private val viewModel: EditProfileViewModel by viewModels()

    private val usernameValidator = ValidatorFactory.getValidator("username")
    private val emailValidator = ValidatorFactory.getValidator("email")
    private val passwordValidator = ValidatorFactory.getValidator("password")

    private val user: User = Application.getUser()!!

    private var imageUpdated = false
    private lateinit var imageUri: Uri
    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageUpdated = true
        imageUri = it!!
        binding.ivProfile.setImageURI(it)
    }

    //¿?
    private var username = user.username
    private var email = user.email
    private var password = user.password

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (user.avatar.isNullOrEmpty()) {
            //Set default avatar
            binding.ivProfile.setImageResource(R.drawable.empty_avatar)
        } else {
            try {
                Picasso.get()
                    .load(user.avatar)
                    .into(binding.ivProfile)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.etUsername.hint = user.username
        binding.etEmail.hint = user.email


        // Listener
        binding.btnEditImage.setOnClickListener {
            contract.launch("image/*")
        }

        binding.etUsername.setOnFocusChangeListener { view, b ->
            if (!b) {
                if (usernameValidator.validate(binding.etUsername.text.toString())) {
                    username = binding.etUsername.text.toString()
                    binding.tvUsernameError.visibility = View.INVISIBLE
                } else {
                    binding.tvUsernameError.visibility = View.VISIBLE
                    binding.tvUsernameError.text = usernameValidator.getError()

                    binding.btnSave.isEnabled = false
                }
            }
        }

        binding.etEmail.setOnFocusChangeListener { view, b ->
            if (!b) {
                if (emailValidator.validate(binding.etEmail.text.toString())) {
                    email = binding.etEmail.text.toString()
                    binding.tvEmailError.visibility = View.INVISIBLE
                } else {
                    binding.tvEmailError.visibility = View.VISIBLE
                    binding.tvEmailError.text = emailValidator.getError()

                    binding.btnSave.isEnabled = false
                }
            }
        }

        binding.etPassword.setOnFocusChangeListener { view, b ->
            if (!b) {
                if (passwordValidator.validate(binding.etPassword.text.toString())) {
                    password = binding.etPassword.text.toString()
                    binding.tvPasswordError.visibility = View.INVISIBLE

                } else {
                    binding.tvPasswordError.visibility = View.VISIBLE
                    binding.tvPasswordError.text = passwordValidator.getError()

                    binding.btnSave.isEnabled = false
                }
            }
        }

        binding.btnSave.setOnClickListener {

            // Save data
            viewModel.saveImageLocal(this, imageUri)

            if (imageUpdated) {
                viewModel.saveImageRemote(imageUri)
            }

            viewModel.updateUser(
                user.id,
                username,
                "",
                "",
                email,
                password
            )
        }

        binding.ibBack.setOnClickListener {
            startActivity(Intent(this, MainScreenActivity::class.java))
        }
    }
}
