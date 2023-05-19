package com.dds.theecogame.presentation.editProfile.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.activity.viewModels
import com.dds.theecogame.databinding.ActivityEditProfileBinding
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.factory.ValidatorFactory
import com.dds.theecogame.domain.model.User
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.editProfile.viewModel.EditProfileViewModel
import java.io.File

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private val viewModel: EditProfileViewModel by viewModels()

    private val usernameValidator = ValidatorFactory.getValidator("username")
    private val emailValidator = ValidatorFactory.getValidator("email")
    private val passwordValidator = ValidatorFactory.getValidator("password")


    private lateinit var imageUri: Uri

    private val user: User = Application.getUser()!!

    //¿?
    private var username = user.username
    private var email = user.email
    private var password = user.password

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load default values from the user
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File(storageDir, "avatar.jpg")
        if (imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            binding.ivProfile.setImageBitmap(bitmap)
        }

        binding.etUsername.hint = user.username
        binding.etEmail.hint = user.email


        // Listener
        binding.btnEditImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
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

//        binding.etUsername.addTextChangedListener {
//            binding.btnSave.isEnabled = (!binding.etUsername.text.toString().isEmpty()
//                    && !binding.etEmail.text.toString().isEmpty()
//                    && !binding.etPassword.text.toString().isEmpty()
//                    && restrictions.checkUsername(binding.etUsername.text.toString())
//                    && restrictions.checkEmail(binding.etEmail.text.toString())
//                    && restrictions.checkPassword(binding.etPassword.text.toString()))
//        }
//        binding.etEmail.addTextChangedListener {
//            binding.btnSave.isEnabled = (!binding.etUsername.text.toString().isEmpty()
//                    && !binding.etEmail.text.toString().isEmpty()
//                    && !binding.etPassword.text.toString().isEmpty()
//                    && restrictions.checkUsername(binding.etUsername.text.toString())
//                    && restrictions.checkEmail(binding.etEmail.text.toString())
//                    && restrictions.checkPassword(binding.etPassword.text.toString()))
//        }
//        binding.etPassword.addTextChangedListener {
//            binding.btnSave.isEnabled = (!binding.etUsername.text.toString().isEmpty()
//                    && !binding.etEmail.text.toString().isEmpty()
//                    && !binding.etPassword.text.toString().isEmpty()
//                    && restrictions.checkUsername(binding.etUsername.text.toString())
//                    && restrictions.checkEmail(binding.etEmail.text.toString())
//                    && restrictions.checkPassword(binding.etPassword.text.toString()))
//        }

        binding.btnSave.setOnClickListener {

            // Save data
            viewModel.saveImageLocal(this, imageUri)

            if (imageFile.exists()) {
                viewModel.saveImageRemote(imageFile.absolutePath)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val imageUri = data?.data
            if (imageUri != null) {

                // Show the imagen
                binding.ivProfile.setImageURI(imageUri)

                this.imageUri = imageUri
            }
        }
    }
}
