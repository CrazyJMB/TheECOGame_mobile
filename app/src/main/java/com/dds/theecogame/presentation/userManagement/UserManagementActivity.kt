package com.dds.theecogame.presentation.userManagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivitySettingBinding
import com.dds.theecogame.databinding.ActivityUserManagementBinding
import com.dds.theecogame.presentation.setting.viewModel.SettingViewModel

class UserManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserManagementBinding

    val viewModel: UserManagementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_management)
    }
}