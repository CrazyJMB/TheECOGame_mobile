package com.dds.theecogame.presentation.register.viewModel

import androidx.lifecycle.ViewModel
import com.dds.theecogame.presentation.game.viewModel.GameViewModel

class RegisterViewModel : ViewModel() {
    fun checkUsername(username: String): String {
        return username
    }

    fun checkEmail(email: String): CharSequence? {
        return email
    }

    fun checkPassword(password: String): CharSequence? {
        return password
    }

}