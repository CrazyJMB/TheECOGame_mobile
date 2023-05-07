package com.dds.theecogame.presentation.mainScreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.dds.theecogame.presentation.editProfile.view.EditProfileActivity
import com.dds.theecogame.R
import com.dds.theecogame.databinding.ActivityMainScreenBinding
import com.dds.theecogame.presentation.mainScreen.viewModel.MainScreenViewModel
import com.dds.theecogame.presentation.game.view.GameActivity
import com.dds.theecogame.presentation.setting.view.SettingActivity
import com.dds.theecogame.presentation.statistics.view.ActivityStatistics

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
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.question_logut)
            builder.setPositiveButton(R.string.alert_confirm) { _, _ ->
                //GlobalScope.launch {
                //    val dataStoreManager = DataStoreManager(dataStore = dataStore)
                //    dataStoreManager.setUserId("")
                //}
                //TODO: ir a la pantalla donde seleccionas iniciar sesion o regristrarse
            }
            builder.setNegativeButton(R.string.alert_cancel) { _, _ ->
                //No hace nada
            }
            builder.show()
        }
    }
}