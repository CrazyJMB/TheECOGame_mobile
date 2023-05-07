package com.dds.theecogame.presentation.editProfile.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dds.theecogame.databinding.ActivityParticipantProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParticipantProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipantProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}