package com.dds.theecogame.presentation.auth.userManagement.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dds.theecogame.databinding.ActivityUserManagementBinding
import com.dds.theecogame.presentation.auth.login.view.LoginActivity
import com.dds.theecogame.presentation.auth.register.view.RegisterActivity
import com.dds.theecogame.presentation.auth.userManagement.viewModel.UserManagementViewModel

class UserManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserManagementBinding

    val viewModel: UserManagementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}