package com.dds.theecogame.presentation.mainScreen.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivityMainScreenBinding
import com.dds.theecogame.domain.Application
import com.dds.theecogame.presentation.editProfile.view.EditProfileActivity
import com.dds.theecogame.presentation.mainScreen.viewModel.MainScreenViewModel
import com.dds.theecogame.presentation.game.view.GameActivity
import com.dds.theecogame.presentation.game.view.ResumeFragment
import com.dds.theecogame.presentation.setting.view.SettingActivity
import com.dds.theecogame.presentation.statistics.view.ActivityStatistics
import com.dds.theecogame.presentation.userManagement.view.UserManagementActivity

class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        binding.btnStatistics.setOnClickListener {
            val intent = Intent(this, ActivityStatistics::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        binding.btnProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.alert_style))
            builder.setTitle(R.string.question_logut)
            builder.setPositiveButton(R.string.alert_confirm) { _, _ ->

                viewModel.endSession()
                val intent = Intent(this, UserManagementActivity::class.java)
                startActivity(intent)

            }
            builder.setNegativeButton(R.string.alert_cancel) { _, _ ->
                //No hace nada
            }
            val alertDialog = builder.create()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()
        }

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                val builder = AlertDialog.Builder(ContextThemeWrapper(this@MainScreenActivity, R.style.alert_style))
                builder.setTitle(R.string.question_logut)
                builder.setPositiveButton(R.string.alert_confirm) { _, _ ->

                    viewModel.endSession()
                    val intent = Intent(this@MainScreenActivity, UserManagementActivity::class.java)
                    startActivity(intent)

                }
                builder.setNegativeButton(R.string.alert_cancel) { _, _ ->
                    //No hace nada
                }
                val alertDialog = builder.create()
                alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                alertDialog.show()
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)
    }
}