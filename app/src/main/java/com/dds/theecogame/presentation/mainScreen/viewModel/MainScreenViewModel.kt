package com.dds.theecogame.presentation.mainScreen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dds.theecogame.domain.model.User

class MainScreenViewModel() : ViewModel() {

    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun login (email: String, password: String): Boolean{

        return true
    }

}


