package com.dds.theecogame.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dds.theecogame.R
import com.dds.theecogame.activity_estadistica

class Questions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
    }

    fun goToStats (view: View){
        val statistics = Intent(this, activity_estadistica::class.java)
        startActivity(statistics)
    }
}