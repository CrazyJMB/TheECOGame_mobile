package com.dds.theecogame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.dds.theecogame.databinding.ActivityEstadisticaBinding
import com.dds.theecogame.databinding.ActivityParticipantProfileBinding

class ActivityParticipantProfile : AppCompatActivity() {
    private lateinit var binding: ActivityParticipantProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipantProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}