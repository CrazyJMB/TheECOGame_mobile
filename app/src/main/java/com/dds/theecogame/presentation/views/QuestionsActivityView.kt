package com.dds.theecogame.presentation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dds.theecogame.R
import com.dds.theecogame.presentation.views.activity_estadistica

class QuestionsActivityView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
    }

    fun goToStats (){
        val statistics = Intent(this, activity_estadistica::class.java)
        startActivity(statistics)
    }
}