package com.dds.theecogame.presentation.editProfile.view

import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.databinding.ActivityEditProfileBinding
import com.dds.theecogame.domain.repository.UserRepository
import com.dds.theecogame.domain.userRestrictions.UserRestrictions
import com.dds.theecogame.presentation.mainScreen.view.MainScreenActivity
import com.dds.theecogame.presentation.editProfile.viewModel.EditProfileViewModel
import com.dds.theecogame.presentation.userManagement.view.UserManagementActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Date
import java.util.Locale

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private val viewModel: EditProfileViewModel by viewModels()
    private lateinit var restrictions: UserRestrictions
    private val userRepository: UserRepository = UserRepositoryImpl()
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        restrictions = UserRestrictions(this)
        setContentView(binding.root)

        // Load default values from the user
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File(storageDir, "avatar.jpg")
        if (imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            binding.ivProfile.setImageBitmap(bitmap)
        }



        binding.btnEditImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
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

        binding.btnSave.setOnClickListener {

            runBlocking(Dispatchers.IO) {
                userRepository.getUser(binding.etEmail.text.toString()).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            userId = it.data?.id!!
                        }

                        is Resource.Error -> {}
                    }
                }

                runBlocking(Dispatchers.IO) {
                    userRepository.updateUser(
                        userId,
                        binding.etUsername.text.toString(),
                        "name",
                        "surname",
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString()
                    ).collect {
                        when (it) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                startActivity(
                                    Intent(
                                        this@EditProfileActivity,
                                        MainScreenActivity::class.java
                                    )
                                )
                            }

                            is Resource.Error -> {

                            }
                        }
                    }
                }
            }

            binding.ibBack.setOnClickListener {
                startActivity(Intent(this, UserManagementActivity::class.java))
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val imageUri = data?.data
            if (imageUri != null) {

                // Show the imagen
                binding.ivProfile.setImageURI(imageUri)

                // Save the image
                val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val imageFile = File(storageDir, "avatar.jpg")

                try {
                    val inputStream = contentResolver.openInputStream(imageUri)
                    val outputStream = FileOutputStream(imageFile)
                    val buf = ByteArray(1024)
                    var len: Int
                    while (inputStream!!.read(buf).also { len = it } > 0) {
                        outputStream.write(buf, 0, len)
                    }
                    inputStream.close()
                    outputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }


            }
        }
    }
}
