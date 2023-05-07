package com.dds.theecogame.presentation.userManagement.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivityMainScreenBinding
import com.dds.theecogame.databinding.ActivityUserManagementBinding
import com.dds.theecogame.presentation.game.view.GameActivity
import com.dds.theecogame.presentation.userManagement.viewModel.UserManagementViewModel

class UserManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserManagementBinding

    val viewModel: UserManagementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManagementBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_user_management)

        binding.btnLogIn.setOnClickListener {
            val intent = Intent(this, /*TODO: Activity login*/GameActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, /*TODO: Activity register*/GameActivity::class.java)
            startActivity(intent)
        }

    }
}